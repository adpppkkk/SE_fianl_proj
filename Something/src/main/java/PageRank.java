// https://codispatch.blogspot.com/2015/12/java-program-implement-google-page-rank-algorithm.html
import java.util.*;
import java.io.*;
public class PageRank {
 
    public static int path[][] = new int[100][10];
    public static double pagerank[] = new double[100];
 	static int tempnode =0;
 	static int nodes =0;
    static String name[] = new String[100];
    static String namepath[][] = new String[100][10];
    static String grouppath[][] = new String[100][10];
    static String group[] = new String[100];

    static int verbosity = 0;
    static int teamsize = 3;
    static int AmountofTeams = 1;


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
	    if (verbosity >= 2)
	    {
	    	System.out.printf("\n Final Page Rank : \n"); 
			for(k=1;k<=totalNodes;k++)
		    {
				System.out.printf(" Page Rank of "+name[k]+" is :\t"+this.pagerank[k]+"\n"); 
		    }
	    }
	
  
    } 

    static void ReadInput(){
    	String tokens[];
    	Scanner in = new Scanner(System.in);
    	String aLine;
	    nodes = 0;
	    //System.out.println(nodes);

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
			nodes++;
		}
		in.close();
		//System.out.println("We have "+nodes+" Nodes!");
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
					//System.out.println("something not null find");
					for(int k=1; k<=nodes;k++)
					{
						if(namepath[i][j].equals(name[k]) && !namepath[i][j].equals(name[i]))
						{
							//System.out.println("find sth");
							path[i][k] = 1;
						}
					}
				}
			}
    	}    
    }

    static void GetUserArguments(String[] args){
		// System.out.println( "try to get arguments" );
		for (int i=0; i< args.length; i++) {
            // System.out.println("i: " + i + " is: " + args[i]);
            
            if (args[i].equals("-v")){
            	verbosity = Integer.parseInt(args[i+1]);
            	// System.out.println( "get verbosity" );
            }if (args[i].equals("-t")){
            	teamsize = Integer.parseInt(args[i+1]);
            	// System.out.println( "get teamsize" );
            }
        }

        // System.out.println( "teamsize is : " + teamsize);
        // System.out.println( "verbosity is : " + verbosity);
	}


	static void Sort()
	{
		String tempname;
		Double temprank;
		String[] temppath;
		for(int i=1; i<=nodes-1; i++)
		{
			for(int j=i; j<=nodes; j++)
			{
				if (pagerank[j] < pagerank[i])
				{
					temprank = pagerank[j];
					tempname = name[j];
					temppath = namepath[j];
					pagerank[j] = pagerank[i];
					pagerank[i] = temprank;
					namepath[j] = namepath[j];
					namepath[i] = temppath;
					name[j] = name[i];
					name[i] = tempname;
				}
			}
		}
		if (verbosity >= 1)
		{
			System.out.printf("\n Final Page Rank after sort: \n"); 
			for(int k=1;k<=nodes;k++)
			{
				System.out.printf(" Page Rank of "+name[k]+" is :\t"+pagerank[k]+"\n"); 
			}
		}		
	}

	static void Grouping()
	{
		if (nodes%teamsize ==0)
			AmountofTeams = nodes/teamsize;
		else
			AmountofTeams = nodes/teamsize +1;

		int index1 = 1;
		int index2 = nodes;
		for (int i=1;i<=nodes;i++)
		{	
			if (i%2 !=0)
				group[i] = name[index1++];
			else
				group[i] = name[index2--];
		}
	}


	public static void Output()
	{
		System.out.println("Pagerank Teams");
		for (int i=1;i<=AmountofTeams;i++)
		{
			System.out.print("Team " + i + ": ");
			for (int j=(i-1)*teamsize+1;j<=i*teamsize;j++)
			{
				if (group[j] != null)
				{
					System.out.print(group[j]+" ");
				}
			}
			System.out.println("");
		}
		System.out.println("");

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
	    GetUserArguments(args);
	    convert();
		
		if (verbosity == 4)
		{
			for(int i=1;i<=nodes;i++)
        	{
        		System.out.println("The " + i+"th student is: "+name[i]);
		    	for(int j=1;j<=nodes;j++)
				{
			    	System.out.print(namepath[i][j]+" ");
				}
				System.out.println("");
			}
		}

		if (verbosity >=3)
		{
			System.out.println("The matrix is:");
			for(int i=1;i<=nodes;i++)
	        {
				for(int j=1;j<=nodes;j++)
				{
				    System.out.print(path[i][j]+" ");
				}
				System.out.println("");
			}
		}

        p.calc(nodes);
        Sort();

        Grouping();
        Output();
        
    }   

}
