package adt;

public class ArrayList<T> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int RESIZE_ARRAY_BY = 5;
    private static final int MAX_EMPTY_THRESHOLD = 5;


    public ArrayList() { // the first arraylist constructer, got limit only 5.
        this(DEFAULT_CAPACITY);

    }

    public ArrayList(int initialCapacity) { // second arrayList constructer, the initial value client set will come here.
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) { //Modified by: Chew Wei Seng
        if (isFull()) {
            incArray();
        }

        array[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) { //Modified by: Chew Wei Seng
        if (isFull()) {
            incArray(); // when it is full the array size will increase
        }

        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {    //Modified by: Chew Wei Seng
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
            array[givenPosition - 1] = null;

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        // Check if the number of empty slots is greater than the threshold
        if (array.length - numberOfEntries > MAX_EMPTY_THRESHOLD) {
            decArray();
        }

        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) { //Modified by: Wong Chai Yee
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (newEntry != null) {
                array[givenPosition - 1] = newEntry;
                return true; // Successful replacement
            } else {
                System.out.println("Invalid entry. Replacement failed.");
                return false; // Invalid entry
            }
        } else {
            System.out.println("Invalid position. Replacement failed.");
            return false; // Invalid position
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }
    
    @Override
    public ListInterface<T> createCopyOfList(){           //Authored by: Lim Shuye
        ListInterface<T> newList = new ArrayList<>();

        for (int i = 0 ; i < array.length; i++) { //copy 
            if(!(array[i] == null)){
                newList.add(array[i]);
            }
            
        }
        
        return newList;
    }

    @Override
    public ListInterface<T> removeDuplicate(ListInterface<T> anotherList) { //Authored by: wong chai yee

        T[] originalList = (T[]) new Object[array.length];
        originalList = this.array;

        ListInterface<T> newList = new ArrayList<>();
        newList = anotherList;
        
        ListInterface<T> updateList = new ArrayList<>();

        for (int i = 0 ; i < array.length; i++) { //copy 
            if(!(originalList[i] == null)){
                updateList.add(originalList[i]);
            }
            
        }

        for (int i = 1; i < array.length; i++) { //original

            for (int j = 1; j <= newList.getNumberOfEntries(); j++) {
                if (!(updateList.contains(newList.getEntry(j)))) {
                    updateList.add(newList.getEntry(j));
                }
            }
        }
        return updateList;
    }

    //function: Used to retrieve array of records that contains the match entry string
    @Override
    public ListInterface<T> search(String anEntry) {        //Authored by: Lim Shuye
        ListInterface<T> result = new ArrayList<>();
        for (T array1 : array) { // loop all records in the list
            if (array1 != null) {
                String temp = array1.toString(); // change the list into string for checking purpose
                anEntry = anEntry.trim(); // remove spaces before, after and add back it to ensure a space is exist before and after

                if (anEntry != null && temp.toUpperCase().contains(anEntry.toUpperCase())) {
                    result.add(array1);

                }

            }
        }
        return result;// return the array of match record
    }

    //function: Used to count number of array of records that contains the match entry string
    @Override
    public int count(String entry) {                        //Authored by: Lim Shuye
    // count how many record that has at least one string in the list

        ListInterface<T> result = search(entry);

        return result.getNumberOfEntries();
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= numberOfEntries + 1; numberOfEntries is array's
     * numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * numberOfEntries; numberOfEntries is array's numberOfEntries before
     * removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    private int compareStrings(String str1, String str2) {          //Authored by: Chew Wei Seng
        int len1 = str1.length();
        int len2 = str2.length();
        int minLength = Math.min(len1, len2);

        for (int i = 0; i < minLength; i++) {
            //compare each character
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            if (char1 != char2) {
                return char1 - char2; // Return the difference of ASCII values
            }
        }

        return len1 - len2; // Strings are equal up to this point, so compare lengths
    }

    @Override
    public void arrangeArrayByOrder() {                              //Authored by: Chew Wei Seng
        int n = numberOfEntries;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare courses based on their string representation
                if (compareStrings(array[j].toString(), array[j + 1].toString()) > 0) {
                    // Swap courses if they are in the wrong order
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void incArray() {                                   //Authored by: Chew Wei Seng
        int newCapacity = array.length + RESIZE_ARRAY_BY;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void decArray() {                                    //Authored by: Chew Wei Seng
        int newCapacity = array.length - RESIZE_ARRAY_BY;
        if (newCapacity < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY; // Ensure we don't shrink below the default capacity
        }

        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, numberOfEntries);
        array = newArray;
    }

}
