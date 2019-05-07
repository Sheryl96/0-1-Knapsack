import java.util.*;

// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{

	
	public void FindSolns(int itemNum)
	{
		
		
		if(crntSoln.wght > inst.GetCapacity())
		{
			return;
		}
		int itemCnt = inst.GetItemCnt();
    
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;
		}
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.TakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.UndoTakeItem(itemNum);
	}
	public KnapsackBTSolver()
	{
		crntSoln = null;
	}
	public void close()
	{
		if (crntSoln != null)
		{
			crntSoln = null;
		}
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		FindSolns(1);
		for(int i = 0; i <= inst.GetItemCnt();i++)
		{
			crntSoln.totalSumValues += inst.GetItemValue(i);
		}
    
	}
}