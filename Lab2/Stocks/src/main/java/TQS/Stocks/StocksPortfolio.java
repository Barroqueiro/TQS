package TQS.Stocks;

import java.util.ArrayList;

public class StocksPortfolio {
	
	private String name;
	
	private ArrayList<Stock> portfolio;
	
	private StockMarket marketService;
	
	public StocksPortfolio(StockMarketService market) {
		portfolio = new ArrayList<Stock>();
		this.marketService = market;
	}

	public StockMarket getStockMarket() {
		return marketService;
	}
	
	public void setMarketService(StockMarket sm) {
		this.marketService = sm;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public double getTotalValue() {
		double res = 0.0;
		for (Stock s: portfolio) {
			res += marketService.getPrice(s.getName()) * s.getQuantity();
		}
		return res;
	}
	
	public void addStock(Stock s) {
		portfolio.add(s);
	}
}
