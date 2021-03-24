package TQS.Stocks;

public class Stock {

		private String name;
		private int quantity;
		
		public Stock(String s, int i) {
			name = s;
			quantity = i;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String s) {
			this.name = s;
		}
		
		public int getQuantity() {
			return this.quantity;
		}
		
		public void setQuantity(int i) {
			this.quantity = i;
		}
		
}
