package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class AddressResolverIT {


    @Test
    void whenGoodAlboiGps_returnAddress() throws ParseException, IOException, URISyntaxException {

        //todo
    	ISimpleHttpClient httpClient = new TqsBasicHttpClient();
    	Mockito.when(httpClient.get("http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=40.640661%2C-8.656688&includeRoadMetadata=true")).thenReturn("{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2021 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2021 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Cais do Alboi\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3800-246\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.6318025,-8.657881|marker-sm-50318A-1&scalebar=true&zoom=15&rand=1879114968\",\"roadMetadata\":null}]}]}");
    	AddressResolver resolver =  new AddressResolver(httpClient);
        //test
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);

        //return
        assertEquals( result, new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );

    }

    @Test
    public void whenBadCoordidates_throwBadArrayindex() throws IOException, URISyntaxException, ParseException {
    	ISimpleHttpClient httpClient = new TqsBasicHttpClient();
    	AddressResolver resolver =  new AddressResolver(httpClient);

        assertThrows(InvalidParameterException.class,() -> resolver.findAddressForLocation(-91, 7));
        assertThrows(InvalidParameterException.class,() -> resolver.findAddressForLocation(91, 7));
        assertThrows(InvalidParameterException.class,() -> resolver.findAddressForLocation(8, 180.2));
        assertThrows(InvalidParameterException.class,() -> resolver.findAddressForLocation(8, -180.1));
        assertDoesNotThrow(() -> resolver.findAddressForLocation(-90, 7),"InvalidParameterException");
        assertDoesNotThrow(() -> resolver.findAddressForLocation(90, 7),"InvalidParameterException");
        assertDoesNotThrow(() -> resolver.findAddressForLocation(8, 180),"InvalidParameterException");
        assertDoesNotThrow(() -> resolver.findAddressForLocation(8, -180),"InvalidParameterException");
    }
    
    @Test
    public void whenBadURI_throwSyntaxError() throws IOException, URISyntaxException, ParseException {
    	ISimpleHttpClient httpClient = new TqsBasicHttpClient();
        //test
    	assertThrows(NullPointerException.class,() -> httpClient.get(null));
    }
}
