
// Branch-and-Bound solver

public class KnapsackBBSolver extends KnapsackBFSolver
{
protected UPPER_BOUND ub;
	
	public KnapsackBBSolver(UPPER_BOUND ub_)
	{
		ub = ub_;

	}
	public void FindSolns(int itemNum)
	{
		int Weight = crntSoln.wght;
		int Weight_New= crntSoln.wght;
		int itemCnt = inst.GetItemCnt();
		int remainingWeight= inst.GetCapacity() - Weight_New;
		int CurrentVal=0;
		
		if(crntSoln.wght > inst.GetCapacity())
		{
			return;
		}
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;
		}
		
		for(int i=0;i<=itemCnt;i++)
		{
			crntSoln.totalSumValues += inst.GetItemValue(i);
		}
		if(ub == UPPER_BOUND.UB1)
		{
			if (crntSoln.totalSumValues - crntSoln.untakenValue <= bestSoln.takenValue)
				return;
		}
		else if(ub == UPPER_BOUND.UB2)
		{
			for(int i=itemNum;i <=itemCnt; i++) 
			{
		    if(( inst.GetCapacity() - crntSoln.wght) >= inst.GetItemWeight(i))
		    {
		    	CurrentVal += inst.GetItemValue(i);
		    }	
			}
		    	    
		    if(crntSoln.takenValue + CurrentVal <= bestSoln.GetValue()) {
		    	return;
		    }
		}
		else if(ub == UPPER_BOUND.UB3)
		{
			double Fractional = 0;
	    	for(int i=itemNum;i<=itemCnt;i++) {
	    		if(inst.GetItemWeight(i) <= (inst.GetCapacity() - Weight_New))
				{
					Fractional +=inst.GetItemValue(i);
					Weight_New +=inst.GetItemWeight(i);
				}
				else
				{
					remainingWeight = (inst.GetCapacity() - Weight_New);
					Fractional += (double) (remainingWeight * (inst.GetItemValue(i)/inst.GetItemWeight(i)));
					Weight_New += inst.GetItemWeight(i);
					break;
				}
	    	}
	    	if((Fractional + crntSoln.takenValue) <= bestSoln.GetValue())
				return;
			
		}
		
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.TakeItem(itemNum);
		FindSolns(itemNum + 1);
		crntSoln.UndoTakeItem(itemNum);
		crntSoln.UndoDontTakeItem(itemNum);
		
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
	}
}