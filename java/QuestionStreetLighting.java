/*
 * 小Q正在给一条长度为n的道路设计路灯安置方案。
 * 为了让问题更简单，小Q把道路视为n个方格，需要照亮的地方用'.'表示，不需要照亮的障碍物格子用'X'表示。
 * 小Q现在要在道路上设置一些路灯，对于安置在pos位置的路灯，这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。
 * 小Q希望能安置尽量少的路灯照亮所有'.'区域，希望你能帮他计算一下最少需要多少盏路灯。
 *
 * Xiao Q is designing a streetlight placement plan for a road of length n.
 * For simplicity, the road is divided into n squares, with '.' representing areas that need lighting and 'X' representing obstacles.
 * Streetlights at position pos illuminate pos - 1, pos, and pos + 1.
 * Xiao Q aims to use the minimum number of streetlights to light all '.' areas, and hopes you can help calculate the minimum number required.
 */
public class QuestionStreetLighting{
	public static int minLightsNeeded(String street){
		char[] array = street.toCharArray();	
		int lightNum = 0;
		int i = 0;
		while(i< array.length){
			if(array[i] == 'X'){
				i++;
			}else{
				lightNum++;
				if(i+1 < array.length && array[i+1] == '.'){
					if(i+2 < array.length && array[i+2] == '.'){
						i+=3;
					}else{
						i+=2;
					}	
				}else{
					i++;
				}
			}
		}
		return lightNum;
	}

	public static void main(String[] args){
	
		String road = "...XX....XX";
		int roadNum1 = minLightsNeeded(road);
		System.out.println(roadNum1);
	}
}
