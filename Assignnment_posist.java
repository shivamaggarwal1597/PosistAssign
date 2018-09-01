import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Assignnment_posist {

	static int total_number_of_nodes;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
	public static Scanner scanner = new Scanner(System.in);

		

				

		//Task 1 

		public static Node createGenesisNode(int node_number){

			   Node n = new Node() ;
			   n.time_now =new Date();

			   // fetching current timestamp
			   n.nodeNumber = node_number;

			   n.nodeId = String.valueOf(node_number);

			   System.out.println("Enter the following data:  \nOwnerId");

			   n.data=new Data();
			   n.data.ownerId=Integer.parseInt(scanner.nextLine());
			   System.out.println("Enter the following data:  \nOwner Value");

			   n.data.value=Float.parseFloat(scanner.nextLine());
			   System.out.println("Enter the following data:  \nOwner Name");

			   n.data.owner_name=scanner.nextLine();

			   n.referenceNodeid = null;

			   n.genesisReferenceNodeid = n;   // This is the Genesis Node so it contains same n.

			   n.HashValue = new HashSet();

			   n.HashValue.add(n);

			   n.sumofchildsofar=0;

			 return n;                  // returning the genesis node for being used by child nodes

			}

		
		//Task 2 & 3

		public static Node addChildNode(int node_number,Node parent,int current_node_number,Node genesisnode){

		       Node prime = new Node();

		         prime.nodeNumber = current_node_number;
		         prime.nodeId = String.valueOf(node_number);

		            System.out.println("Enter the following data:  \nOwnerId ");

		            prime.data=new Data();

		        prime.data.ownerId=Integer.parseInt(scanner.nextLine());
		        System.out.println("Enter the following data:  \nOwner Value ");


		        prime.data.value=Float.parseFloat(scanner.nextLine());
		        System.out.println("Enter the following data:  \nOwner Name ");
		        prime.data.owner_name=scanner.nextLine();

		        while(prime.data.value>parent.data.value-parent.sumofchildsofar) {

		        	System.out.println("Hey You have not followed the tree property please enter again its value");

		        	prime.data.value=Float.parseFloat(scanner.nextLine());

		        }

		        prime.referenceNodeid = parent ;      //This node's parent address will be &l

		        prime.genesisReferenceNodeid = genesisnode;   //copying genesis node address we received.

		        prime.HashValue = new HashSet();

				prime.HashValue.add(prime);

				parent.sumofchildsofar=parent.sumofchildsofar+prime.data.value;

				   return prime;

		       

		}

		

		//Task 3 & Task 4 

		public static void encrypt(Node node_n){
		    node_n.isEncrypted = true;

		    System.out.print("Enter owner's password");

		    node_n.password=scanner.nextLine();

		    //encrypt by adding 2 to change ASCII value of character.

		    for(int i = 0; (i < 100 && i< node_n.password.length() ); i++) {

		    	char newchar=(char) ((int)node_n.password.charAt(i) + 2);

		        n.password=n.password.substring(0,i)+newchar+node_n.password.substring(i+1); 

		    }





		}

		

		//Task 3 & Task 4

		public static boolean decrypt(Node given_node,String password){

			//decrypt by subtracting 2 to change ASCII value of character.

	        for(int i = 0; (i < 100 && i < password.length()); i++) {

	        	char newchar=(char) ((int)password.charAt(i) - 2);

		        password=password.substring(0,i)+newchar+password.substring(i+1); 
	        }
	        if(given_node.password == password){
	            return true;
	        }
	        else{
	            return false;
	        }

	}

		public static void change_owner(Node given_node){
		    String pass_key;
		    System.out.println("Enter Owner one password");
		    pass_key=scanner.nextLine();
		    if(decrypt(given_node,pass_key)){
		        System.out.println("Ownership of the Node will be changed, Please enter the new owner id");
		        given_node.data.owner_name=scanner.nextLine();
		        encrypt(given_node);

		    }

		}

		

		//task 8 longest chain of genesis code
		public static int longest_chainofgenesisnode(Node node){
			int h = -1;
			for (Node child : node.childReferenceNodeid) {

				int ch = longest_chainofgenesisnode(child);

				if (ch > h) {

					h = ch;

				}

			}
			return h + 1; 

		}

		

		//task 9 
		int calc_diameter(Node ptr)

		{
		    // Base case

		    if (ptr== null)

		        return 0;

		    // Find top two highest children

		    int max1 = 0, max2 = 0;

		    for (Node node:ptr.childReferenceNodeid)

		    {

		        int h = depthOfTree(node);

		        if (h > max1) {

		           max2 = max1; 

		           max1 = h;

		        }else if (h > max2)

		           max2 = h;

		    }

		 

		    // Iterate over each child for diameter

		    int maxChildDia = 0;

		    for (Node node : ptr.childReferenceNodeid)

		        maxChildDia = Math.max(maxChildDia, calc_diameter(node));

		 

		    return Math.max(maxChildDia, max1 + max2 + 1);

		}

		

		//SubFuntion To get depth of my tree

		int depthOfTree(Node ptr)

		{

		    // Base case

		    if (ptr== null)

		        return 0;

		 

		    int maxdepth = 0;

		 

		    // Check for all children and find

		    // the maximum depth

		    for (Node node :ptr.childReferenceNodeid)

		 

		        maxdepth = Math.max(maxdepth , depthOfTree(node));

		 

		    return maxdepth + 1;

		}

		

		public static void merge_node(Node node1, Node node2){

		    //Add Data of both nodes.

		    // Use DFS to calculate the longer chain from two.

		    //Which is larger ,its password is retained.

		}



		

		//class to store the data members 

		

		static class Data{

			

			int ownerId;

		    float value;

		    String owner_name; 

		    HashSet<Data> hashset;

		}

		

		//Node Structure 

		

		static class Node{

			

			Date time_now ;

		    Data data ;

		    String nodeId ;

		    int nodeNumber ;

		    Node referenceNodeid ;

		    ArrayList<Node> childReferenceNodeid;                 // Array of addresses.

		    Node genesisReferenceNodeid;

		    HashSet<Node> HashValue;         // Hash of value of the set.

		    boolean isEncrypted;                       //Will block access to node if it has been encrypted.

		    String password;

		    float sumofchildsofar;        //to keep the track of the sum of its child elements.

		}

		
		public static void main(String[] args) {

			

			 total_number_of_nodes++; 

			 Node gen = createGenesisNode(total_number_of_nodes);

			 total_number_of_nodes++;

			Node child= addChildNode(1,gen,total_number_of_nodes,gen);

			total_number_of_nodes++;

			Node child2= addChildNode(2,child,total_number_of_nodes,gen);

			

			encrypt(child);

			

			encrypt(child2);

			

			boolean result=decrypt(child, "Vijay Garg");

			

			if(result==true) {

				

				System.out.println("Node Decrypted Successfully");

			}

			 

			 

		}


	}

