package org.kosa.myproject.model;

import org.kosa.myproject.common.CommonOutputLogging;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private CommonOutputLogging logger = new CommonOutputLogging();
	
	@Override
	public void updateProduct() {
		System.out.println(getClass().getName()+" core concern updateProduct");
	}
	@Override
	public void findProductById() {
		logger.log(getClass().getName(), "findProductById");
		System.out.println(getClass().getName()+" core concern findProductById");
	}
	@Override
	public void findProductByName() {
		logger.log(getClass().getName(), "findProductByName");

		System.out.println(getClass().getName()+" core concern findProductByName");
	}
	@Override
	public void findProductListByMaker() {
		logger.log(getClass().getName(), "findProductListByMaker");

		System.out.println(getClass().getName()+" core concern findProductListByMaker");
	}
}
