package uk.ac.ox.map.explorer.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePagePlace extends Place {
	
	/*
	 * Allows an Activity to be accessible via a URL.
	 * The PlaceTokenizer knows how to serialize 
	 * the state of the place to a url token
	 */
	public static class Tokenizer implements PlaceTokenizer<HomePagePlace>
	{

		/*
		 * Serializes the place 
		 */
		@Override
		public String getToken(HomePagePlace place)
		{
			return "None";
		}

		/*
		 * Deserializes a place 
		 */
		@Override
		public HomePagePlace getPlace(String token)
		{
			return new HomePagePlace();
		}

	}

}
