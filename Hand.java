// The following group of comments was made on March 17, 2022 to provide context for this submission.

// The original assignment can be summarized as the generation of an array of virtual playing cards
// and then determining what hands are within that set of cards.
// The lines of code that were originally provided with the assignment (i.e. the lines that weren't mine)
// are the method declarations, any comments that include asterisks, and the black box "java.util.ArrayList"
// which is used to grade the finished code based on an array of simulated inputs, and additionally functions like a console.

// The final grade for this assignment received a score of 38/42.



/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }

	  
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand[position] = null;
   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   

   /******************************** Implement your methods here ****************************************/
   public static void main(String [] args) {
	   Hand myHand = new Hand();
	   Card card1 = new Card(1,3);
	   Card card2 = new Card(5,3);
	   Card card3 = new Card(4,3);
	   Card card4 = new Card(3,3);
	   Card card5 = new Card(2,3);
	   
	   myHand.addCard(card1);
	   myHand.addCard(card2);
	   myHand.addCard(card3);
	   myHand.addCard(card4);
	   myHand.addCard(card5);
	   
	   System.out.println(myHand.numPairs());
	   System.out.println(myHand.hasTriplet());
	   System.out.println(myHand.hasFlush());
	   System.out.println(myHand.hasStraight());
	   System.out.println(myHand.hasFullHouse());
	   System.out.println(myHand.hasFourOfAKind());
	   System.out.println(myHand.highestValue());
	   
   }
   public int numPairs() {
	   sortByValue();
	   int counter = 0;
	   for (int card = 0; card < hand.length;card++) {
		   for (int comparisonCard =card + 1; comparisonCard < hand.length;comparisonCard++) {
			   if ( hand[card].getValue() == hand[comparisonCard].getValue()) {
				   counter++; //not perfect, yet, some combinations fall through
				   if (comparisonCard == card+1) {
					   card++;
				   }
				   break;
			   }
		   }
	   }
	   return counter;
   }
   public boolean hasTriplet() {
	   int cardValSave = 0;
	   boolean check = false;
	   boolean possibleTriplet = false;
	   for (int card = 0; card<hand.length;card++) {
		   for (int comparisonCard = card + 1; comparisonCard<hand.length; comparisonCard++) {
			   if (possibleTriplet == true) {
				   if (hand[card].getValue() != cardValSave) {
					   possibleTriplet = false;
				   }
			   }
			   if (hand[card].getValue()==hand[comparisonCard].getValue() && possibleTriplet == false) {
				   possibleTriplet = true;
				   cardValSave = hand[card].getValue();
			   }
			   else if (hand[card].getValue()==hand[comparisonCard].getValue() && possibleTriplet == true && hand[card].getValue() == cardValSave) {
				   check = true;
			   }
		   }
	   }
	   if (check == false) {
		   return false;
	   }
	   else {
		   return true;
	   }
   }
   public boolean hasFlush() {
	   int check = hand[0].getSuit();
	   int checkAgainst;
	   int counter = 0;
	   for (int card = 0; card<hand.length;card++) {
		   checkAgainst = hand[card].getSuit();
		   if (checkAgainst != check) {
			   return false;
		   }
	   }
	   return true;
   }
   public boolean hasStraight() {
	   for (int card = 1; card<hand.length;card++) {
		   if (hand[card].getValue() != hand[card-1].getValue()+1) {
			   return false;
		   }
	   }
	   return true;
   }
   public boolean hasFullHouse() {
	   if (numPairs() == 2 && hasTriplet()==true && hasFourOfAKind() == false) {
		   return true;
	   }
	   return false;
   }
   public boolean hasFourOfAKind() {
	   sortByValue();
	   if (numPairs() == 2 && hasTriplet()==true) {
		   if(hand[0].getValue() == hand[3].getValue() || hand[1].getValue()==hand[4].getValue()) {
			   return true;
		   }
	   }
	   return false;
   }
   public Card highestValue() {
	   sortByValue();
	   if (hand[0].getValue() == 1) {
		   if (hasStraight()==true) {
			   return hand[4];
		   }
		   else if (hasStraight() == false) {
			   return hand[0];
		   }
	   }
	   return hand[4];   
   }
   public Card highestDuplicate() {
	   sortByValue();
	   if (numPairs()>0 || hasTriplet()==true) {
		   if (hand[1].getValue()==1) {
			   return hand[1];
		   }
		   for(int card = 0; card<hand.length;card++) {
			   if (card < hand.length-1) {
				   if (hand[card].getValue() == hand[card+1].getValue()) {
					   return hand[card];
				   }
			   }
		   }
	   }
	   return null;
   }
   public int compareTo(Hand h) {
	   h.sortByValue();
	   sortByValue();
	   if (hasStraight() == true && hasFlush()==true || h.hasStraight()==true && h.hasFlush()==true) {
		   if (hasStraight() == true && hasFlush() == true && h.hasStraight() == false || h.hasFlush()==false) {
			   return 1;
		   }
		   else if (h.hasStraight() == true && h.hasFlush() == true && hasStraight() == false || hasFlush()==false) {
			   return -1;
		   }
		   else if (hasStraight()==true&& hasFlush() == true && h.hasStraight()==true && h.hasFlush()==true && hand[0].getValue() == 1 && h.hand[0].getValue() == 1) {   
			   return 0;
		   }
	   }
	   else if (hasStraight() == true && hasFlush()==true || h.hasStraight()==true && h.hasFlush()==true) {
		   if (hasStraight() == true && hasFlush() == true && h.hasStraight() == false || h.hasFlush()==false) {
			   return 1;
		   }
		   else if (h.hasStraight() == true && h.hasFlush() == true && hasStraight() == false || hasFlush()==false) {
			   return -1;
		   }
		   else if (hasStraight()==true&& hasFlush() == true && h.hasStraight()==true && h.hasFlush()==true && hand[0].getValue() == 1 && h.hand[0].getValue() == 1) {   
			   if (h.highestDuplicate().getValue() > highestDuplicate().getValue()) {
				   return -1;
			   }
			   else if (h.highestDuplicate().getValue()<highestDuplicate().getValue()){
				   return 1;
			   }
			   else {
				   return 0;
			   }
		   }
	   }
	   else if (hasFourOfAKind() == true || h.hasFourOfAKind()==true) {
		   if (hasFourOfAKind() == true && h.hasFourOfAKind() == false) {
			   return 1;
		   }
		   else if (hasFourOfAKind() == false && h.hasFourOfAKind() == true) {
			   return -1;
		   }
		   else if (hasFourOfAKind()==true&&h.hasFourOfAKind()==true) {   
			   if (h.highestDuplicate().getValue() > highestDuplicate().getValue()) {
				   return -1;
			   }
			   else {
				   return 1;
			   }
		   }
	   }
	   else if (hasFullHouse() == true || h.hasFullHouse()==true) {
		   if (hasFullHouse() == true && h.hasFullHouse() == false) {
			   return 1;
		   }
		   else if (hasFullHouse() == false && h.hasFullHouse() == true) {
			   return -1;
		   }
		   else if (hasFullHouse()==true&&h.hasFullHouse()==true) {   
			   if (h.hand[2].getValue() > hand[2].getValue()) {
				   return -1;
			   }
			   else {
				   return 1;
			   }
		   }
	   }
	   else if (hasFlush() == true || h.hasFlush()==true) {
		   if (hasFlush() == true && h.hasFlush() == false) {
			   return 1;
		   }
		   else if (hasFlush() == false && h.hasFlush() == true) {
			   return -1;
		   }
		   else if (hasFlush()==true&&h.hasFlush()==true) {   
			   if (h.highestValue().getValue() > highestValue().getValue()) {
				   return -1;
			   }
			   else {
				   return 1;
			   }
		   }
	   }
	   else if (hasStraight() == true || h.hasStraight()==true) {
		   if (hasStraight() == true && h.hasStraight() == false) {
			   return 1;
		   }
		   else if (hasStraight() == false && h.hasStraight() == true) {
			   return -1;
		   }
		   else if (hasStraight()==true&&h.hasStraight()==true) {   
			   if (h.highestValue().getValue() > highestValue().getValue()) {
				   return -1;
			   }
			   else if(h.highestValue().getValue() < highestValue().getValue()){
				   return 1;
			   }
			   else {
				   return 0;
			   }
		   }
	   }
	   else if (hasTriplet() == true || h.hasTriplet()==true) {
		   if (hasTriplet() == true && h.hasTriplet() == false) {
			   return 1;
		   }
		   else if (hasTriplet() == false && h.hasTriplet() == true) {
			   return -1;
		   }
		   else if (hasTriplet()==true&&h.hasTriplet()==true) {   
			   if (h.hand[2].getValue() > hand[2].getValue()) {
				   return -1;
			   }
			   else {
				   return 1;
			   }
		   }
	   }
	   else if (numPairs() == 2 || h.numPairs()== 2) {
		   if (numPairs() == 2 && h.numPairs() < 2) {
			   return 1;
		   }
		   else if (numPairs() < 2 && h.numPairs() == 2) {
			   return -1;
		   }
		   else if (numPairs()== 2&&h.numPairs()== 2) {   
			   if (Math.max(h.hand[1].getValue(), h.hand[3].getValue()) > Math.max(hand[1].getValue(), hand[3].getValue())) {
				   return -1;
			   }
			   else {
				   return 1;
			   }
		   }
	   }
	   else if (numPairs() == 1 || h.numPairs()== 1) {
		   if (numPairs() == 1 && h.numPairs() < 1) {
			   return 1;
		   }
		   else if (numPairs() < 1 && h.numPairs() == 1) {
			   return -1;
		   }
		   else if (numPairs()== 1&&h.numPairs()== 1) {   
			   if (highestDuplicate().getValue() < h.highestDuplicate().getValue()) {
				   return -1;
			   }
			   else if (highestDuplicate().getValue() > h.highestDuplicate().getValue()){
				   return 1;
			   }
			   else {
				   return 0;
			   }
		   }
	   }
	   else if (numPairs() == 0 && h.numPairs()== 0) {
		   if (highestValue().getValue() > h.highestValue().getValue()) {
			   return 1;
		   }
		   else if (highestValue().getValue() < h.highestValue().getValue()){
			   return -1;
		   }
		   else {
			   return 0;
		   }
	   }
	   return 0;
   }
}
