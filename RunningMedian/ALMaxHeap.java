/*****************************************************
 * class ALHeap
 * Implements a min heap using an ArrayList as underlying container
 *****************************************************/

import java.util.ArrayList;

public class ALMaxHeap {

    //instance vars
    private ArrayList<Double> _heap; //underlying container is array of Integers
    private int _size;

    /*****************************************************
     * default constructor  ---  inits empty heap
     *****************************************************/
    public ALMaxHeap() 
    { 
	_heap = new ArrayList<Double>();
	_size = 0;
    }



    /*****************************************************
     * toString()  ---  overrides inherited method
     * Returns either 
     * a) a level-order traversal of the tree (simple version)
     * b) ASCII representation of the tree (bit more complicated, much more fun)
     *****************************************************/
    public String toString() 
    { 
	//simple version:
	//return _heap.toString(); 

	//prettier version:
	String lvlOrdTrav = "heap size " + _heap.size() + "\n";

	if ( _heap.size() == 0 ) return lvlOrdTrav;

	int h = 1; //init height to 1
	for( int i = 0; i < _heap.size(); i++ ) {
	    lvlOrdTrav += i + ":" + _heap.get(i) + " ";
	    if ( i >= Math.pow(2,h) - 2 ) {
		lvlOrdTrav += "\n";
		h++;
	    }
	}
	return lvlOrdTrav;
    }//O(n)



    /*****************************************************
     * boolean isEmpty()
     * Returns true if no meaningful elements in heap, false otherwise
     *****************************************************/
    public boolean isEmpty() { return _heap.isEmpty(); } //O(1)



    /*****************************************************
      * double peekMax()
      * Returns min value in heap
      * Postcondition: Heap remains unchanged.
      *****************************************************/
    public double peekMax() { 
	if ( _heap.size() < 1 )
	    return -1.0;
	else
	    return _heap.get(0); 
    } //O(1)

    public int size(){
	return _size;
    }



    /*****************************************************
     * add(double) 
     * Inserts an element in the heap
     * Postcondition: Tree maintains heap property.
     *****************************************************/
    public void add( double addVal ) { 

	//Add value as last node, to maintain balance, completeness of tree
	_heap.add( addVal );
	_size++;

	int addValPos = _heap.size() - 1;
	int parentPos;

	while( addValPos > 0 ) { //potentially swap until reach root

	    //pinpoint parent
	    parentPos = (addValPos-1) / 2;

	    if ( addVal > _heap.get(parentPos) ) {//addVal < parent
		swap( addValPos, parentPos );
		addValPos = parentPos;
	    }
	    else 
		break;
	}
    } //O(logn)



    /*****************************************************
     * removeMin()  ---  means of removing an element from heap
     * Removes and returns least element in heap.
     * Postcondition: Tree maintains heap property.
     *****************************************************/
    public double removeMax() {

	if ( _heap.size() == 0 ) 
	    return -1.0;
	else
	    _size--;

	//store root value for return at end of fxn
	double retVal = peekMax();

	//store val about to be swapped into root
	double foo = _heap.get( _heap.size() - 1);

	//swap last (rightmost, deepest) leaf with root
	swap( 0, _heap.size() - 1 );

	//lop off last leaf
	_heap.remove( _heap.size() - 1);

	// walk the now-out-of-place root node down the tree...
	int pos = 0;
	int maxChildPos;

	while( pos < _heap.size() ) {

	    //choose child w/ max value, or check for child
	    maxChildPos = maxChildPos(pos);

	    //if no children, then i've walked far enough
	    if ( maxChildPos == -1 ) 
		break;
	    //if i am less than my least child, then i've walked far enough
	    else if ( foo >= _heap.get(maxChildPos) ) 
		break;
	    //if i am < max child, swap with that child
	    else {
		swap( pos, maxChildPos );
		pos = maxChildPos;
	    }
	}
	//return removed value
	return retVal;
    }//O(logn)



    /*****************************************************
     * maxChildPos(int)  ---  helper fxn for removeMax()
     * Returns index of least child, or 
     * -1 if no children, or if input pos is not in ArrayList
     * Postcondition: Tree unchanged
     *****************************************************/
    private int maxChildPos( int pos ) {
	int retVal;
	int lc = 2*pos + 1; //index of left child
	int rc = 2*pos + 2; //index of right child

	//pos is not in the heap or pos is a leaf position
	if ( pos < 0 || pos >= _heap.size() || lc >= _heap.size() )
	    retVal = -1;
	//if no right child, then left child is only option for max
	else if ( rc >= _heap.size() )
	    retVal = lc;
	//have 2 children, so compare to find greatest 
	else if ( _heap.get(lc) > _heap.get(rc) )
	    retVal = lc;
	else
	    retVal = rc;
	return retVal;
    }//O(1)



    //************ aux helper fxns ***************
    private double maxOf( double a, double b ) {
	if ( a > b )
	    return a;
	else
	    return b;
    }

    //swap for an ArrayList
    private void swap( int pos1, int pos2 ) {
	_heap.set( pos1, _heap.set( pos2, _heap.get(pos1) ) );	
    }
    //********************************************



    //main method for testing
    public static void main( String[] args ) {
	/*
	ALMaxHeap pile = new ALMaxHeap();

	pile.add(2);
	System.out.println(pile);
	pile.add(4);
	System.out.println(pile);
	pile.add(6);
	System.out.println(pile);
	pile.add(8);
	System.out.println(pile);
	pile.add(10);
	System.out.println(pile);
	pile.add(1);
	System.out.println(pile);
	pile.add(3);
	System.out.println(pile);
	pile.add(5);
	System.out.println(pile);
	pile.add(7);
	System.out.println(pile);
	pile.add(9);
	System.out.println(pile);

	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMax() + "...");
	System.out.println(pile);
	*/
    }//end main()

}//end class ALHeap
