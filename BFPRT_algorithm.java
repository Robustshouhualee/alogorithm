package BFPRT;

public class BFPRT_algorithm {

	public static void swap(int[] a,int high,int low){ //交换数组中两个数的位置
		int temp;
		temp=a[low];
		a[low]=a[high];
		a[high]=temp;
	}
	public static void sort(int[] a,int left,int right){ //简单的冒泡排序
		for(int i=left;i<right;i++){
			for(int j=i;j<right;j++){
				if(a[j]<a[i]){
					swap(a,j,i);
				}
			}
		}
	}
	public static int partition(int[] a,int left,int right,int pivotid){ //根据BFPRT算法选出的中位数作为主元来划分数据

		//划分出以小于主元和大于主元两部分
		swap(a,pivotid,right-1);
		System.out.println("partition begin: "+" left="+left+" right="+right);
		for(int i=left;i<right;i++){
			System.out.print(a[i]+"  ");
		    }
			System.out.println();
			
		int j=left;
		System.out.println("j="+j);
		for(int i=left;i<right;i++){
			if(a[i]<a[right-1]){
				swap(a,i,j);
				j++;
				System.out.println("swap: "+i+" "+a[i]+"  "+(j-1)+"  "+a[j-1]);
			}
		}
		System.out.println("privotid swap: "+j+" "+a[j]+"  "+(right)+"  "+a[right-1]);
		swap(a,j,right-1);
		return j;
	}
	public static int bfprt(int[] a,int left,int right,int id){  //核心算法，BFPRT算法
		System.out.println("bfprt begin"+" left="+left+" right="+right);
		for(int i=left;i<right;i++){
		System.out.print(a[i]+"  ");
	    }
		System.out.println();
		
		
		if(right-left<=5){  //若数组长度小于等于5，直接输出中位数
			sort(a,left,right);
			System.out.print("'<5'   ");
			for(int i=left;i<right;i++){
				System.out.print(a[i]+"  ");
			    }
				System.out.println();
			return a[id];
		}
		int temp=left;
		for(int start=left,end=start+5;end<right;start+=5,end+=5){  //找出数组中每一份的中位数
			sort(a,start,end);
			swap(a,temp,start+2);
			temp++;
		}
		

		System.out.println("left="+left+"   temp="+temp);
		int pivotid=(left+temp)/2;    //选取中位数的中位数
		System.out.println("pivotid="+pivotid+"   a[pivotid]="+a[pivotid]);
		bfprt(a, left, temp, pivotid);
		
		System.out.print("bfprt end  ");
		for(int i=left;i<right;i++){
			System.out.print(a[i]+"  ");
		    }
			System.out.println();
			
			
		int m=partition(a,left,right,pivotid);   //以中位数作为主元，划分数据，返回主元所处的位置。
		
		System.out.print("partition end: ");
		for(int i=left;i<right;i++){
			System.out.print(a[i]+"  ");
		    }
			System.out.println();
			System.out.println("m="+m+"  id="+id);
			//判断主元的位置是否和所求位置相等或大于或小于，并分别对此作出相应的处理	
		if(m==id){		
			return a[m];
		}
		else if(m<id){
			System.out.println("exe m<id");
			return bfprt(a,m,right,id);
		}
		else if(m>id){
			return bfprt(a,left,m,id);
		}
		return -1;
		
	}
	
	public static void main(String[] args){
		int[] a={100,52,554,12,454,843,7,8,6,10,21,25,16,35,9,17,85,86,8256};
		int len=a.length;
		System.out.println("len="+len);
		//bfprt(a,0,len,len/5);
		System.out.print("original  ");
		for(int i=0;i<len;i++){
		System.out.print(a[i]+"  ");
	}
		System.out.println();
		System.out.println("result="+bfprt(a,0,len,15));
		System.out.print("result  ");
		for(int i=0;i<len;i++){
		System.out.print(a[i]+"  ");
	}
		
	}
}
