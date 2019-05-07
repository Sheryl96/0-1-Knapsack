import java.util.*;

// Dynamic programming solver
public class KnapsackDPSolver implements java.io.Closeable
{
	private KnapsackInstance inst;
	private KnapsackSolution soln;

	public void KnapsackDPSolver()
	{
		int itemCnt = inst.GetItemCnt();
		
		int cap = inst.GetCapacity();

		int [][]t = new int[itemCnt+1][cap+1];
		//Fill 0 if capacity is 0
		for (int col=0; col<=cap; col++){
			t[0][col] = 0;
		}
		//Fill 0 if no items selected
		for (int row=0; row<= itemCnt; row++){
			t[row][0] = 0;
		}

		for (int i =1; i<=itemCnt; i++)
		{
			t[i][0]=0;
			int weights = inst.GetItemWeight(i);
			int values = inst.GetItemValue(i);
			for(int j=1; j<= cap; j++){
				if (weights > j){
					t[i][j] = t[i-1][j];
				}
				else{
					t[i][j] = Math.max(values+t[i-1][j-weights], t[i-1][j]);
				}
			}
		}
		int j=cap;
		for(int i = itemCnt; i>=1;i--)
		{
			if(t[i][j] > t[i-1][j])
			{
				soln.TakeItem(i);
				j = j- inst.GetItemWeight(i);

			}
		}
		soln.ComputeValue();
	}
	public void close()
	{

	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		soln = soln_;
		KnapsackDPSolver();
	}
	
}