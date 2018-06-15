import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author CHIN LUNG
 */
public class Calculator {

    public Double ans(String e)
    {
        	Stack s = new Stack(e.length());
		String [] data =transfer(e);//傳入轉換的方法得到後置式的分割陣列
		for(int i = 0 ; i < data.length ; i++)//讀出後置式的各個運算元運算子來做計算
		{
			if(data[i] == null)break;
//是運算子時
		if(data[i].equals(""+"")||data[i].equals(""-"")||data[i].equals(""*"")||data[i].equals(""/""))
			{
				int b = Integer.parseInt(s.pop());//提出前面兩個儲存的值計算
				int a = Integer.parseInt(s.pop());
				if(data[i].equals(""+""))
					s.push(""""+(a+b));
				else if(data[i].equals(""-""))
					s.push(""""+(a-b));
				else if(data[i].equals(""*""))
					s.push(""""+(a*b));
				else if(data[i].equals(""/""))
					s.push(""""+(a/b));
			}
			else//是運算元時存入堆疊
				s.push(data[i]);

		}
                return Double.parseDouble(s.pop());
    }
	
	public  String [] transfer(String data)//轉換成後置式
	{
                Stack s = new Stack(data.length());
		int index = 0;//為了將輸入的字串分割成陣列儲存用的索引值
		String [] ans = new String[data.length()];
		for(int i = 0 ; i < data.length() ;i++)
		{
			String opr = returnSplit(data,i);//傳入分割方法取得各個運算元或運算子
			if(opr.length() > 1)i+= (opr.length()-1);//避免傳出的是兩位數以上I未對應
//是運算子的話看其優先順序 是疊入還是取出
		if(opr.equals(""+"")||opr.equals(""-"")||opr.equals(""*"")||opr.equals(""/"")||opr.equals(""^""))
			{
				while(s.index != -1 && priority(opr) <= priority(s.peep()))
				{
					ans[index++] = s.pop();
				}
				s.push(opr);
			}
			else if(opr.equals(""(""))
			{
				s.push(opr);
			}
			else if(opr.equals("")""))//取出運算子直到對應到""(""以後
			{
				String tempS ="""";
				while(s.index!= -1 &&!( (tempS = s.pop()).equals(""("")) )
				{
					ans[index++] = tempS;
				}
			}
			else
			{
				ans[index++] =opr;
			}
		}
		while(s.index!=-1)ans[index++] = s.pop();
		return ans;
	}
	public static String returnSplit(String data,int index)//傳回分割後的值
	{
		String thisString ="""";
		String s = String.valueOf(data.charAt(index));

		if(s.equals(""+"")||s.equals(""-"")||s.equals(""*"")||s.equals(""/"")||s.equals(""("")||s.equals("")"")||s.equals(""^""))
		{
			return s;//直接傳回運算子
		}
		else
		{
			do{
				thisString += s;
				if(index+1 ==data.length())break;
				s = String.valueOf(data.charAt(++index));//後面的位數式運算元就累加
			}while(!s.equals(""+"")&&!s.equals(""-"")&&!s.equals(""*"")&&!s.equals(""/"")&&!s.equals(""("")&&!s.equals("")"")&&!s.equals(""^""));
			return thisString;
		}
	}
	public static int priority(String opr)//運算子的優先順序
	{
		if(opr.equals(""^""))return 3;
		else if(opr.equals(""*"")||opr.equals(""/""))return 2;
		else if(opr.equals(""+"")||opr.equals(""-""))return 1;
		else return 0;
	}

class Stack
{
	String [] stack;
	public int index;
	Stack(int max)
	{
		index = -1; //無元素
		stack = new String[max];
	}
	public void push(String data)
	{
		stack[++index] = data;
	}
	public String pop()
	{
		return stack[index--];
	}
	public String peep()
	{
		return stack[index];
	}
}
    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
}

