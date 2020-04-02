// https://codispatch.blogspot.com/2015/12/java-program-implement-google-page-rank-algorithm.html
import java.util.*;
import java.io.*;
public class PageRank {
 
    public static int path[][] = new int[10][10];
    public double pagerank[] = new double[10];
 	static int tempnode =0;
 	static int nodes =0;
    static String name[] = new String[10];
    static String namepath[][] = new String[10][10];


    public void calc(double totalNodes){
    
	double InitialPageRank;
	double OutgoingLinks=0; 
	double DampingFactor = 0.85; 
	double TempPageRank[] = new double[10];

	int ExternalNodeNumber;
	int InternalNodeNumber; 
	int k=1; // For Traversing
	int ITERATION_STEP=1;

	InitialPageRank = 1/totalNodes;
	System.out.printf(" Total Number of Nodes :"+totalNodes+"\t Initial PageRank  of All Nodes :"+InitialPageRank+"\n");
 
	// 0th ITERATION  _ OR _ INITIALIZATION PHASE
	for(k=1;k<=totalNodes;k++)
	    {
		this.pagerank[k]=InitialPageRank;
	    }   
  
	System.out.printf("\n Initial PageRank Values , 0th Step \n");
	for(k=1;k<=totalNodes;k++)
	    {
		System.out.printf(" Page Rank of "+name[k]+" is :\t"+this.pagerank[k]+"\n");
	    }  
  
	while(ITERATION_STEP<=2) // Iterations
	    {
		// Store the PageRank for All Nodes in Temporary Array 
		for(k=1;k<=totalNodes;k++)
		    {  
			TempPageRank[k]=this.pagerank[k];
			this.pagerank[k]=0;
		    }
    
		for(InternalNodeNumber=1;InternalNodeNumber<=totalNodes;InternalNodeNumber++)
		    {
			for(ExternalNodeNumber=1;ExternalNodeNumber<=totalNodes;ExternalNodeNumber++)
			    {
				if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1)
				    { 
					k=1;
					OutgoingLinks=0;  // Count the Number of Outgoing Links for each ExternalNodeNumber
					while(k<=totalNodes)
					    {
						if(this.path[ExternalNodeNumber][k] == 1 )
						    {
							OutgoingLinks=OutgoingLinks+1; // Counter for Outgoing Links
						    }  
						k=k+1;  
					    } 
					// Calculate PageRank     
					this.pagerank[InternalNodeNumber]+=TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);    
				    }
			    }  
		    }    
     
		System.out.printf("\n After "+ITERATION_STEP+"th Step \n");
  
		for(k=1;k<=totalNodes;k++) 
		    System.out.printf(" Page Rank of "+name[k]+" is :\t"+this.pagerank[k]+"\n"); 
  
		ITERATION_STEP = ITERATION_STEP+1;
	    }

	// Add the Damping Factor to PageRank
	for(k=1;k<=totalNodes;k++)
	    {
		this.pagerank[k]=(1-DampingFactor)+ DampingFactor*this.pagerank[k]; 
	    } 
  
	// Display PageRank
	System.out.printf("\n Final Page Rank : \n"); 
	for(k=1;k<=totalNodes;k++)
	    {
		System.out.printf(" Page Rank of "+name[k]+" is :\t"+this.pagerank[k]+"\n"); 
	    }
  
    } 

    static void ReadInput(){
    	String tokens[];
    	Scanner in = new Scanner(System.in);
    	String aLine = in.nextLine();
	    tokens = aLine.split(",");
	    nodes = Integer.parseInt(tokens[0]);
	    System.out.println(nodes);

	    PageRank p = new PageRank();
        while ( in.hasNext() ) 
        {
	        aLine = in.nextLine();
	        tokens = aLine.split(",");	       	      
	        name[++tempnode] = tokens[0];
			for(int a=1;a<tokens.length;a++)
			{
				namepath[tempnode][a]=tokens[a];				   
			}
		}
		in.close();
    }

    static void convert(){
    	for(int i=1;i<=nodes;i++)
    	for(int j=1;j<=nodes;j++)
    	{
    		path[i][j]=0;
    	}

    	for(int i=1;i<=nodes;i++)
    	{
			for(int j=1;j<=nodes;j++)
			{
				if(namepath[i][j] != null)
				{
					System.out.println("something not null find");
					for(int k=1; k<=nodes;k++)
					{
						if(namepath[i][j].equals(name[k]) && !namepath[i][j].equals(name[i]))
						{
							System.out.println("find sth");
							path[i][k] = 1;
						}
					}
				}
			}
    	}    
    }

    public static void main(String args[])
    {
        // int i,j,cost;
        // int tempnode =0;
        // String name[] = new String[10];
        // String namepath[][] = new String[10][10];
        //System.out.println("Enter the Number of WebPages \n");
	    //System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: \n");
	    PageRank p = new PageRank();
	    ReadInput();
	    convert();
		for(int i=1;i<=nodes;i++)
        {
        	System.out.println("The " + i+"th student is: "+name[i]);
		    for(int j=1;j<=nodes;j++)
			{
			    System.out.print(namepath[i][j]+" ");
			}
			System.out.println("");
		}

		for(int i=1;i<=nodes;i++)
        {
		    for(int j=1;j<=nodes;j++)
			{
			    System.out.print(path[i][j]+" ");
			}
			System.out.println("");
		}
        p.calc(nodes);
   
          
    }   

}
