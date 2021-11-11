package test.edu.wou.algorithms;

import edu.wou.algorithms.BinarySearchTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer, Integer> binarySearchTree;
    private List<Integer> actualOrder = new ArrayList<>();
    private Consumer visit = key -> actualOrder.add((Integer) key);

    @Before
    public void setUp() throws Exception {
        binarySearchTree = new BinarySearchTree<>();
        /*
                 49
               /    \
              10     50
             /  \      \
            4    22     67
        */
        binarySearchTree.insert(49, 49);
        binarySearchTree.insert(10, 10);
        binarySearchTree.insert(50, 50);
        binarySearchTree.insert(4, 4);
        binarySearchTree.insert(22, 22);
        binarySearchTree.insert(67, 67);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert_ShouldONLYInsertTheSameValueNodeOnce() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(49,49);
        tree.insert(49,49);
        var size = tree.size();

        assertEquals(1, size);
    }
    @Test
    public void insert_ShouldONLYInsertTheSameValueNodeOnceStreeTest() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(5,5);
        tree.insert(49,49);
        tree.insert(5,5);
        tree.insert(49,49);
        tree.insert(70,70);
        tree.insert(5,5);
        tree.insert(49,49);
        tree.insert(70,70);
        var size = tree.size();

        assertEquals(3, size);

        var expectedOrder = new Integer[] {5,49,70};
        tree.inOrderTraversal(visit);
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void insert_ShouldInsertSixNodeLinkListFault() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(1,1);
        tree.insert(2,2);
        tree.insert(3,3);
        tree.insert(4,4);
        tree.insert(5,5);
        tree.insert(6,6);
        var size = tree.size();

        assertEquals(6, size);
        assertEquals(6, tree.getHeight());
    }

    @Test
    public void size_ShouldbeZeroForAnEmptyTree(){
        var tree = new BinarySearchTree<Integer, Integer>();

        assertEquals(0, tree.size());
    }

    @Test
    public void size_ShouldbeSixForInstanceTree(){
        assertEquals(6, binarySearchTree.size());
    }

    @Test
    public void remove_RootNotefromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {22, 10, 50, 4, 67 };

        binarySearchTree.remove(49);
        binarySearchTree.breadthFirstTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_removing10fromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {4, 22, 49, 50, 67 };

        binarySearchTree.remove(10);
        binarySearchTree.inOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_removing4fromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {10, 22, 49, 50, 67};

        binarySearchTree.remove(4);
        binarySearchTree.inOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_removing22fromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {4, 10, 49, 50, 67};

        binarySearchTree.remove(22);
        binarySearchTree.inOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_removing50fromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {4, 10, 22, 49, 67};

        binarySearchTree.remove(50);
        binarySearchTree.inOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_removing67fromInstanceTree() throws InvalidKeyException {
        var expectedOrder = new Integer[] {4, 10, 22, 49, 50};

        binarySearchTree.remove(67);
        binarySearchTree.inOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());

        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_case3_theNodeHasAnEmptyRightAndFullLeft() throws InvalidKeyException{
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(5,5);
        tree.insert(6,6);
        tree.insert(7,7);
        tree.insert(2,2);
        tree.insert(1,1);

        //11, 12, 18, 20, 22, 25
        tree.remove(2);
        var expectedOrder = new Integer[] {1,5,6,7};
        tree.inOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void remove_case4_theNodeHasAnFullRightAndEmptyLeft() throws InvalidKeyException{
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(5,5);
        tree.insert(6,6);
        tree.insert(7,7);
        tree.insert(2,2);
        tree.insert(1,1);
        tree.insert(9,9);
        tree.insert(8,8);

        tree.remove(7);
        var expectedOrder = new Integer[] {1,2,5,6,8,9};
        tree.inOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void find_ShouldFind10InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(10);
        Integer expected = 10;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldFind67InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(67);
        Integer expected = 67;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldFind49InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(49);
        Integer expected = 49;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldFind4InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(4);
        Integer expected = 4;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldFind22InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(22);
        Integer expected = 22;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldFind50InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(50);
        Integer expected = 50;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldNotFind9InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(9);
        Integer expected = null;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldNotFind100InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(100);
        Integer expected = null;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void find_ShouldNotFind0InInstanceTree() throws InvalidKeyException {

        var found = binarySearchTree.find(0);
        Integer expected = null;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void preOrderTraversal_ShouldWorkOnInstanceTree() {
        var expectedOrder = new Integer[] {49, 10, 4, 22, 50, 67};

        binarySearchTree.preOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void preOrderTraversal_ShouldWorkOnSlideTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(20,20);
        tree.insert(12,12);
        tree.insert(11,11);
        tree.insert(18,18);
        tree.insert(25,25);
        tree.insert(22,22);

        var expectedOrder = new Integer[] {20, 12, 11, 18, 25, 22};
        tree.preOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void preOrderTraversal_ShouldWorkOnEmptyTree(){
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.preOrderTraversal(visit);

        assertEquals(0, actualOrder.size());
    }

    @Test
    public void inOrderTraversal_ShouldWorkOnInstanceTree() {
        var expectedOrder = new Integer[] {4, 10, 22, 49, 50, 67};
        binarySearchTree.inOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void inOrderTraversal_ShouldWorkOnLectureSlideTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(20,20);
        tree.insert(12,12);
        tree.insert(11,11);
        tree.insert(18,18);
        tree.insert(25,25);
        tree.insert(22,22);

        var expectedOrder = new Integer[] {11, 12, 18, 20, 22, 25};
        tree.inOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void inOrderTraversal_ShouldWorkOnEmptyTree(){
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.inOrderTraversal(visit);

        assertEquals(0, actualOrder.size());
    }

    @Test
    public void postOrderTraversal_ShouldWorkOnInstanceTree() {
        var expectedOrder = new Integer[] {4, 22, 10, 67, 50, 49};

        binarySearchTree.postOrderTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void postOrderTraversal_ShouldWorkOnSlideTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(20,20);
        tree.insert(12,12);
        tree.insert(11,11);
        tree.insert(18,18);
        tree.insert(25,25);
        tree.insert(22,22);

        var expectedOrder = new Integer[] {11, 18, 12, 22, 25, 20};
        tree.postOrderTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void postOrderTraversal_ShouldWorkOnEmptyTree(){
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.postOrderTraversal(visit);

        assertEquals(0, actualOrder.size());
    }

    @Test
    public void breadthFirstTraversal_ShouldWorkOnInstanceTree() {
        var expectedOrder = new Integer[] {49, 10, 50, 4, 22, 67};

        binarySearchTree.breadthFirstTraversal(visit);

        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void breadthFirstTraversal_ShouldWorkOnSlideTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(20,20);
        tree.insert(12,12);
        tree.insert(11,11);
        tree.insert(18,18);
        tree.insert(25,25);
        tree.insert(22,22);

        var expectedOrder = new Integer[] {20, 12, 25, 11, 18, 22};
        tree.breadthFirstTraversal(visit);
        assertEquals(expectedOrder.length, actualOrder.size());
        for( var i = 0; i < expectedOrder.length; ++i){
            assertEquals(expectedOrder[i], actualOrder.get(i));
        }
    }

    @Test
    public void breadthFirstTraversal_ShouldWorkOnEmptyTree(){
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.breadthFirstTraversal(visit);

        assertEquals(0, actualOrder.size());
    }


    @Test
    public void getHeight_shouldBeZeroonEmpty() {
        var tree = new BinarySearchTree<Integer, Integer>();

        var height = tree.getHeight();

        assertEquals(0, height);
    }

    @Test
    public void getHeight_shouldBeOneWIthOnlyRoot() {
        var tree = new BinarySearchTree<Integer, Integer>();
        tree.insert(5,5);
        var height = tree.getHeight();

        assertEquals(1, height);
    }

    @Test
    public void getHeight_shouldBeTwoWIthTHreeNodeTriangle() {
        var tree = new BinarySearchTree<Integer, Integer>();
        tree.insert(5,5);
        tree.insert(4,4);
        tree.insert(6,6);
        var height = tree.getHeight();

        assertEquals(2, height);
    }

    @Test
    public void getHeight_shouldBeThree() {
        var height = binarySearchTree.getHeight();

        assertEquals(3, height);
    }

    @Test
    public void getHeight_shouldBeFour() {
        binarySearchTree.insert(1,1);

        var height = binarySearchTree.getHeight();

        assertEquals(4, height);
    }

    @Test
    public void getHeight_shouldBeFive() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(5,5);
        tree.insert(6,6);
        tree.insert(7,7);
        tree.insert(2,2);
        tree.insert(1,1);
        tree.insert(9,9);
        tree.insert(8,8);

        var height = tree.getHeight();

        assertEquals(5, height);
    }

    @Test
    public void isFull_shouldFailonInstanceTree() {
        assertFalse(binarySearchTree.isFull());
    }

    @Test
    public void isFull_shouldFailonLinkedListTypeTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(1,1);
        tree.insert(2,2);
        tree.insert(3,3);
        tree.insert(4,4);

        assertFalse(tree.isFull());
    }

    @Test
    public void isFull_ShouldWOrkonBasicTreeWithThreeNodesSoZeroAndTwo() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(2,2);
        tree.insert(1,1);
        tree.insert(3,3);

        assertTrue(tree.isFull());
    }

    @Test
    public void isFull_shouldworkOnSingleNodeTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(20,20);

        assertEquals(tree.isFull(), true);
    }

    @Test
    public void isFull_shouldworkOnSampleTreeFromAssignmentRunDown() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(7,7);
        tree.insert(1,1);
        tree.insert(3,3);
        tree.insert(5,5);
        tree.insert(6,6);
        tree.insert(0,0);
        tree.insert(2,2);
        tree.insert(4,4);
        tree.insert(11,11);
        tree.insert(12,12);
        tree.insert(9,9);
        tree.insert(8,8);
        tree.insert(10,10);

        assertEquals(tree.isFull(), true);
    }

    @Test
    public void isPerfect_ShouldWorkWithLabRunDownExample() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(3,3);
        tree.insert(1,1);
        tree.insert(0,0);
        tree.insert(2,2);
        tree.insert(5,5);
        tree.insert(4,4);
        tree.insert(6,6);

        assertEquals(tree.isPerfect(), true);

    }

    @Test
    public void isPerfect_ShouldFailwithInstancetree() {

        assertEquals(binarySearchTree.isPerfect(), false);

    }

    @Test
    public void isPerfect_ShouldWorkwithTreeThatOnlyHasRoot() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(3,3);
        assertEquals(tree.isPerfect(), true);

    }

    @Test
    public void isPerfect_ShouldFailwithEmptyTree() {
        var tree = new BinarySearchTree<Integer, Integer>();

        assertEquals(tree.isPerfect(), false);

    }

    @Test
    public void isPerfect_ShouldWorkWIthAnotherLevelOfNodes() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(50,50);
        tree.insert(40,40);
        tree.insert(30,30);
        tree.insert(20,20);
        tree.insert(60,60);
        tree.insert(70,70);
        tree.insert(80,80);

        tree.insert(35,35);
        tree.insert(45,45);
        tree.insert(43,43);
        tree.insert(46,46);

        tree.insert(55,55);
        tree.insert(65,65);
        tree.insert(54,54);
        tree.insert(56,56);

        assertEquals(tree.isPerfect(), true);

    }

    @Test
    public void isPerfect_ShouldFailWIthAnotherLevelOfNodes1() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(50,50);
        tree.insert(40,40);
        tree.insert(30,30);
        tree.insert(20,20);
        tree.insert(60,60);
        tree.insert(70,70);
        tree.insert(80,80);

        tree.insert(35,35);
        tree.insert(45,45);
        tree.insert(43,43);
        tree.insert(46,46);

        tree.insert(55,55);
        tree.insert(65,65);
        tree.insert(54,54);


        assertEquals(tree.isPerfect(), false);

    }

    @Test
    public void isComplete_shouldWorkWIthLabExample() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(3,3);
        tree.insert(1,1);
        tree.insert(0,0);
        tree.insert(2,2);
        tree.insert(5,5);
        tree.insert(4,4);
        
        assertEquals(tree.isComplete(), true);
    }

    @Test
    public void isComplete_ShouldFailOnBottomLevel() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(3,3);
        tree.insert(1,1);
        tree.insert(0,0);
        tree.insert(2,2);
        tree.insert(5,5);

        assertEquals(tree.isComplete(), false);
    }

    @Test
    public void isComplete_ShouldFailOnSingleNode() {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(3,3);

        assertEquals(tree.isComplete(), false);
    }

    @Test
    public void isComplete_ShouldWorkWithAnotherLayerOfNodes() throws InvalidKeyException {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(50,50);
        tree.insert(40,40);
        tree.insert(30,30);
        tree.insert(20,20);
        tree.insert(60,60);
        tree.insert(70,70);
        tree.insert(80,80);

        tree.insert(35,35);
        tree.insert(45,45);
        tree.insert(43,43);
        tree.insert(46,46);

        tree.insert(55,55);
        tree.insert(65,65);
        tree.insert(54,54);
        tree.insert(56,56);

        tree.remove(80);

        assertEquals(tree.isComplete(), true);
    }

    @Test
    public void isComplete_ShouldWorkfailAnotherLayerOfNodes() throws InvalidKeyException {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(50,50);
        tree.insert(40,40);
        tree.insert(30,30);
        tree.insert(20,20);
        tree.insert(60,60);
        tree.insert(70,70);
        tree.insert(80,80);

        tree.insert(35,35);
        tree.insert(45,45);
        tree.insert(43,43);
        tree.insert(46,46);

        tree.insert(55,55);
        tree.insert(65,65);
        tree.insert(54,54);
        tree.insert(56,56);

        tree.remove(40);

        assertEquals(tree.isComplete(), false);
    }

    @Test
    public void isComplete_ShouldWorkfailagainAnotherLayerOfNodes() throws InvalidKeyException {
        var tree = new BinarySearchTree<Integer, Integer>();

        tree.insert(50,50);
        tree.insert(40,40);
        tree.insert(30,30);
        tree.insert(20,20);
        tree.insert(60,60);
        tree.insert(70,70);
        tree.insert(80,80);

        tree.insert(35,35);
        tree.insert(45,45);
        tree.insert(43,43);
        tree.insert(46,46);

        tree.insert(55,55);
        tree.insert(65,65);
        tree.insert(54,54);
        tree.insert(56,56);

        tree.remove(60);

        assertEquals(tree.isComplete(), false);
    }
}