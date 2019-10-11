
public class myStack <T> {
	
	private T[] myArray;
	private int topIndex;

	@SuppressWarnings("unchecked")
	public myStack (int size)
	{
		topIndex = 0;
		myArray = (T[])(new Object[size]);
	}
	
	public T pop()
	{
		if(topIndex >= 0) 
		{
			int temp = topIndex;
			topIndex--;
			return myArray[temp];
		}
		else {return null;}
	}
	public void push(T inp)
	{
		if(topIndex < myArray.length-1) 
		{
			topIndex++;
			myArray[topIndex] = inp;
		}
		else {expand(); push(inp);}
	}
	public int getStackSize()
	{
		return topIndex;
	}
	
	private void expand()
	{
		@SuppressWarnings("unchecked")
		T[] temp = (T[])(new Object[myArray.length+10]);
		for(int i=0; i<myArray.length; i++)
		{
			temp[i] = myArray[i];
		}
		myArray = temp;
	}
}
