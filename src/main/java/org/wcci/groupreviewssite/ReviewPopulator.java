package org.wcci.groupreviewssite;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner{

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	
	public void run(String... args) throws Exception {
		
		//Random question, do we need to add review names to the categories right above here? 
		Category wine = new Category("Winery");
		wine = categoryRepo.save(wine);
		
		Category beer = new Category("Brewery");
		beer = categoryRepo.save(beer);
		
		Category spirits = new Category("Distillery");
		spirits = categoryRepo.save(spirits);
		
		Review powellVillageWinery = new Review("Powell Village Winery", "Description", "Contact Info", "Image Url", wine);
		powellVillageWinery = reviewRepo.save(powellVillageWinery);
		Review wineOnHigh = new Review("Wine On High", "Description", "Contact Info", "Image Url", wine);
		wineOnHigh = reviewRepo.save(wineOnHigh);
		Review wolfsRidgeBrewing = new Review("Wolf's Ridge Brewing", "Description", "Contact Info", "Image Url", beer);
		wolfsRidgeBrewing = reviewRepo.save(wolfsRidgeBrewing);
		Review northHighBrewing = new Review("North High Brewing", "Description", "Contact Info", "Image Url", beer);
		northHighBrewing = reviewRepo.save(northHighBrewing);
		Review middleWestSpirits = new Review("Middle West Spirits", "Description", "Contact Info", "Image Url", spirits);
		middleWestSpirits = reviewRepo.save(middleWestSpirits);
		Review fourFiftyOneSpirits = new Review("451 Spirits", "Description", "Contact Info", "Image Url", spirits);
		fourFiftyOneSpirits = reviewRepo.save(fourFiftyOneSpirits);
	}
}
