package org.wcci.groupreviewssite;

import javax.annotation.Resource;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.groupreviewssite.models.Category;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.models.Tag;
import org.wcci.groupreviewssite.repositories.CategoryRepository;
import org.wcci.groupreviewssite.repositories.ReviewRepository;
import org.wcci.groupreviewssite.repositories.TagRepository;

@Component
public class ReviewPopulator implements CommandLineRunner{

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {

		//Random question, do we need to add review names to the categories right above here?
	
		Review powellVillageWinery = new Review("Powell Village Winery", "Description", "Contact Info", "Image Url");
		powellVillageWinery = reviewRepo.save(powellVillageWinery);
		Review wineOnHigh = new Review("Wine On High", "Description", "Contact Info", "Image Url");
		wineOnHigh = reviewRepo.save(wineOnHigh);
		Review wolfsRidgeBrewing = new Review("Wolf's Ridge Brewing", "Description", "Contact Info", "Image Url");
		wolfsRidgeBrewing = reviewRepo.save(wolfsRidgeBrewing);
		Review northHighBrewing = new Review("North High Brewing", "Description", "Contact Info", "Image Url");
		northHighBrewing = reviewRepo.save(northHighBrewing);
		Review middleWestSpirits = new Review("Middle West Spirits", "Description", "Contact Info", "Image Url");
		middleWestSpirits = reviewRepo.save(middleWestSpirits);
		Review fourFiftyOneSpirits = new Review("451 Spirits", "Description", "Contact Info", "Image Url");
		fourFiftyOneSpirits = reviewRepo.save(fourFiftyOneSpirits);


		Category wine = new Category("Winery", powellVillageWinery, wineOnHigh);
		wine = categoryRepo.save(wine);
		Category beer = new Category("Brewery", wolfsRidgeBrewing, northHighBrewing);
		beer = categoryRepo.save(beer);
		Category spirits = new Category("Distillery", middleWestSpirits, fourFiftyOneSpirits);
		spirits = categoryRepo.save(spirits);



		tagRepo.save(new Tag("#WineWinning", powellVillageWinery, wineOnHigh));
		tagRepo.save(new Tag("#BrewBoss", wolfsRidgeBrewing, northHighBrewing));
		tagRepo.save(new Tag("DistillingDreams", middleWestSpirits, fourFiftyOneSpirits));

	}
}
