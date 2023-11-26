package mainPackage;

public class Questions {

	public static void main (String[]args){
		 DB_SetUp.createNewDatabase();
		 
			//---------------------------------------------------------------------------------------------------------------
			 //mainPackage.Questions and answers creation (note: fix powers, binary base numbers, finish q5 CompOrg)
			//---------------------------------------------------------------------------------------------------------------
			
			 DB_CreateTables.CreateStandardTables();

			 card csquestion1 = new card("CSE1", "Which of these is the correct symbol for an alternative in BNF syntax?", "::=; Φ;|;=", 2, 1, 1);
			 DB_CardInteract.addCardToDB(csquestion1);
			 
			 card csquestion2 = new card("CSE2", "What does ROBDD stand for?", "Reduced and Ordered Binary Decision Diagram; Ramified and Ordered Binary Decision Diagram; Rounded and Ordered Boolean Decision Diagram; Reduced and Ordered Boolean Decision Diagram", 0, 1, 1);
			 DB_CardInteract.addCardToDB(csquestion2);
			 
			 card csquestion3 = new card("CSM3", "What are the three relations required to classify a relation as a partial order?", "Reflexive, symmetric, transitive; Reciprocal, symmetrical, translative; Reflexive, anti-symmetrical, transitive; Reciprocal, anti-symmetrical, transitive", 2, 1, 2);
			 DB_CardInteract.addCardToDB(csquestion3);
			 
			 card csquestion4 = new card("CSM4", "Which of these is semantically equivalent to x = 12*2?", "y = 12*2; x = 12 + 12; x = 10*2; x = x*x", 1, 1, 2);
			 DB_CardInteract.addCardToDB(csquestion4);
			 
			 card csquestion5 = new card("CSH5", "Every meet semi-lattice element pair has ... ?", "A supremum; An infimum; Both a supremum and an infimum; Neither a supremum or an infimum", 1, 1, 3);
			 DB_CardInteract.addCardToDB(csquestion5);
			 
			 card csquestion6 = new card("CSH6", "Which of these represent a lattice homomorphism", "f (aVb) = f (a) V f (b);"
			 		+ "f (a∧b) = f (a) ∧ f (b); f (b∧a) = f (b) V f (a);"
			 		+ "f (bVa) = f (b) ∧ f (a); f (aVb) = f (a) ∧ f (b);"
			 		+ "f (a∧b) = f (a) V f (b); f (a∧b) = f (a) ∧ f (b);"
			 		+ "¬f (a∧b) = ¬f (a) ∧ f (b)", 0, 1, 3);
			 DB_CardInteract.addCardToDB(csquestion6);
			 
			//---------------------------------------------------------------------------------------------------------------
			 
			 card dmquestion1 = new card("DME1", "A x B is {(1, 3), (1, 4), (1, 5), (2, 3), (2, 4), (2, 5)} What is the name of this set?", "The Cardinal set; The Power set; The Cartesian product; The Relation set", 2, 2, 1);
			 DB_CardInteract.addCardToDB(dmquestion1);
			
			 card dmquestion2 = new card("DME2", "True or false: You can obtain the inverse of a bijective function?", "True; False", 0, 2, 1);
			 DB_CardInteract.addCardToDB(dmquestion2);
			
			 card dmquestion3 = new card("DMM3", "Which of these is De Morgan's Law?", "¬ ( p V q )  ≡  ¬ p ∧ ¬ q; p V ( p ∧ q ) ≡ p; p →  q ≡ ¬ p V q;( p V q ) V r ≡ p V ( q V r )", 0, 2, 2);
			 DB_CardInteract.addCardToDB(dmquestion3);
			 
			 card dmquestion4 = new card("DMM4", "Addition and multiplication are both commutative in regards to matrices?", "True; False; No addition is not commutative; No multiplication is not commutative", 3, 2, 2);
			 DB_CardInteract.addCardToDB(dmquestion4);
			
			 card dmquestion5 = new card("DMH5", "What is the set builder notation for { 0, 4, 16, 36, 64, 100 }?", "{ x | x∈Z ∧ 4x }; { x | x∈R  ∧ 2x }; { x | x∈Z  ∧ x = p4, p∈Z ∧ p%2 = 1}; { x | x∈Z , x = p2, p∈Z ∧ p%2 = 0 }", 3, 2, 3);
			 DB_CardInteract.addCardToDB(dmquestion5);
			 
			 card dmquestion6 = new card("DMH6", "True or false: ¬ (p V (¬ p ∧ q)) ≡ ¬ p ∧ ¬ q?", "True; False", 0, 2, 3);
			 DB_CardInteract.addCardToDB(dmquestion6);
			 
				//---------------------------------------------------------------------------------------------------------------
			 
			 card coquestion1 = new card("COE1", "What part of computer hardware holds the registers?", "RAM; CPU; Motherboard; deprecCLI.GUI", 1, 3, 1);
			 DB_CardInteract.addCardToDB(coquestion1);
			 
			 card coquestion2 = new card("COE2", "What is the flow of instructions to be operated on a digital computer?", "Registers - RAM - Cache - Disk; RAM - Disk - Registers - Cache; Registers- Cache - RAM - Disk; Disk - RAM - Cache - Registers", 3, 3, 1);
			 DB_CardInteract.addCardToDB(coquestion2);
			 
			 card coquestion3 = new card("COM3", "Which of these is a ongoing problem in the technological world today?", "There are multithreaded applications but no multithreaded CPUs to maximise efficiency; Chips are becoming too big; The rapid advancement of technology; Too many resources", 2, 3, 2);
			 DB_CardInteract.addCardToDB(coquestion3);
			 
			 card coquestion4 = new card("COM4", "What is the purpose of cache?", "It improves the efficiency of data retrieval; It decreases the speed of the CPU to match the speed of the RAM; It increases the speed of the RAM to match the speed of the CPU; It stores and manipulates data during the execution of instructions", 0, 3, 2);
			 DB_CardInteract.addCardToDB(coquestion4);
			 
			 card coquestion5 = new card("COH5", "Which of these operations run?", "ld %r1, %r2; ld [var], %r15; add %r1,      %r2; ld [var], %r2 ", 3, 3, 3);
			 DB_CardInteract.addCardToDB(coquestion5);
			 
			 card coquestion6 = new card("COH6", "What is 4f916 in binary?", "10111110002; 100111110012; 010110110102; 100111110002", 1, 3, 3);
			 DB_CardInteract.addCardToDB(coquestion6);
			 
			//---------------------------------------------------------------------------------------------------------------
			 //mainPackage.Questions and answers creation end
			//---------------------------------------------------------------------------------------------------------------
			 
	}
	
	

	


}
