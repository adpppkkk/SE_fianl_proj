// https://codispatch.blogspot.com/2015/12/java-program-implement-google-page-rank-algorithm.html
import java.util.*;
import java.io.*;
public class PageRank {
 
    public int path[][] = new int[10][10];
    public double pagerank[] = new double[10];
 
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
		System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
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
		    System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
  
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
		System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
	    }
  
    } 

    public static void main(String args[])
    {
        int nodes,i,j,cost;
        int tempnode =0;
        String tokens[];
        String name[] = new String[10];
        String namepath[][] = new String[10][10];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Number of WebPages \n");
        String aLine = in.nextLine();
	    tokens = aLine.split(",");
	    nodes = Integer.parseInt(tokens[0]);
	    System.out.println(nodes);
	    System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: \n");
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

		for(i=1;i<=nodes;i++)
        {
        	System.out.println(name[i]);
		    for(j=1;j<=nodes;j++)
			{
			    System.out.println(namepath[i][j]);
			}
		}
        //p.calc(nodes);
   
          
    }   

}
