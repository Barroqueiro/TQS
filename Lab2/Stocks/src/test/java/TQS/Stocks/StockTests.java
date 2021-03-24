package TQS.Stocks;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.*;

/**
 * Unit test for Stocks.
 */
public class StockTests 
{
	
    @SuppressWarnings("deprecation")
	@Test
    public void getTotalValueWithMocks()
    {
        StockMarketService market = (StockMarketService) Mockito.mock(StockMarketService.class);
    
        StocksPortfolio portfolio = new StocksPortfolio(market);
        
        Mockito.when(market.getPrice("EBAY")).thenReturn(4.0);
        Mockito.when(market.getPrice("MSFT")).thenReturn(1.5);
        
        portfolio.addStock(new Stock("EBAY",2));
        portfolio.addStock(new Stock("MSFT",4));
        double result = portfolio.getTotalValue();
        assertThat(result,is(14.0));
        Mockito.verify(market,Mockito.times(2)).getPrice(Mockito.anyString());
    }
}
