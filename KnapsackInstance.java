import java.util.*;

public class KnapsackInstance implements java.io.Closeable
{
	private int itemCnt; //Number of items
	private int cap; //The capacity
	private int[] weights; //An array of weights
	private int[] values; //An array of values

	public KnapsackInstance(int itemCnt_)
	{
		itemCnt = itemCnt_;

		weights = new int[itemCnt + 1];
		values = new int[itemCnt + 1];
		cap = 0;
	}
	public void close()
	{
		weights = null;
		values = null;
	}
	public void Sort() 
	{
		int temp_values,temp_weights;
		double RatioTemp;
		double[] ArrayRatio = new double[GetItemCnt()+1];
		ArrayRatio[0] = 0;
		for(int i=1; i<GetItemCnt()+1; i++)
		{
			ArrayRatio[i] = ((double)values[i])/((double)weights[i]);
		}
		for(int i=1; i<GetItemCnt()+1; i++)
	      {
			int j;
			RatioTemp=ArrayRatio[i];
			temp_values=values[i];
			temp_weights=weights[i];
	         for(j=i-1;j>=1;)
	         {
	            if(ArrayRatio[j]<RatioTemp)
	            {
	            	ArrayRatio[j+1]=ArrayRatio[j]; 
	               values[j+1]=values[j];
	               weights[j+1]=weights[j];
	               j--;
	            }
	            else
	            {
	               break;
	            }
	         }
	         ArrayRatio[j+1]=RatioTemp;
	         values[j+1]=temp_values;
	         weights[j+1]=temp_weights;

	      }
    }
	public void Generate()
	{
		int i;
        int wghtSum;

		weights[0] = 0;
		values[0] = 0;

		wghtSum = 0;
		for(i=1; i<= itemCnt; i++)
		{
			weights[i] = Math.abs(RandomNumbers.nextNumber()%100 + 1);
			values[i] = weights[i] + 10;
			wghtSum += weights[i];
		}
		cap = wghtSum/2;
	}

	public int GetItemCnt()
	{
		return itemCnt;
	}
	public int GetItemWeight(int itemNum)
	{
		return weights[itemNum];
	}
	public int GetItemValue(int itemNum)
	{
		return values[itemNum];
	}
	public int GetCapacity()
	{
		return cap;
	}
	public void Print()
	{
		int i;

		System.out.printf("Number of items = %d, Capacity = %d\n",itemCnt, cap);
		System.out.print("Weights: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",weights[i]);
		}
		System.out.print("\nValues: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",values[i]);
		}
		System.out.print("\n");
	}
}
