package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.dao.util.DAOFinals;
import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public boolean addNewProduct(Product newProduct) throws ServiceException {
		if (!validation(newProduct)) { return false;}
		try {
			return productDAO.save(newProduct);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> getProducts(int row) throws ServiceException {
		try {
			return productDAO.findAll(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long countProducts() throws ServiceException {
		try {
			return productDAO.countAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Product findProductWithId(long id) throws ServiceException {
		try {
			return productDAO.findOne(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findProductsWithIds(List<Reserve> reservations) throws ServiceException {
		try {
			List<Product> products = new ArrayList<>();
			for(Reserve reservation : reservations){
				Product product = productDAO.findOne(reservation.getProductId());
				product.setReserveId(reservation.getId());
				products.add(product);
			}
			return products;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findProductsWithIds(Order order, int currentPage) throws ServiceException {
		try {
			/*
			getting string of products
			*/
			String productIds = order.getProductIds();
			String [] productIdsArray = productIds.split(",");
			/*
			getting all types of products according to the the order's products ids field
			 */
			List<Product> allProducts = productDAO.findProductsByIds(productIds);

			//Page page = new Page(String.valueOf(currentPage), productIdsArray.length);

			/*
			add products that matches productIdsArray Id
			 */
			List<Product> products = new ArrayList<>();
			for (int i = 0; i > productIdsArray.length; i++) {
				Product specificProduct = allProducts.get(i);
				if (Long.valueOf(productIdsArray[i]) == specificProduct.getId()) {
					products.add(specificProduct);
				}
			}

////////////////////////////////
//			String[] productIdsArray = order.getProductIds().split(",");
//
//			//due to currentPage get productIDsArray part if(1)[1-15] - if(2)[16-30] - if(3)[31-45] - if(4)[46-48]...
//			int reminder = productIdsArray.length % page.getMaxRowsAtPage();
//			int finArrlength = (page.getCurrentPage() < page.getMaxPage()) || reminder == 0 ?
//					page.getMaxRowsAtPage() : reminder;
//
//			long[] productIDs = new long[finArrlength];
//			for(int i = 0; i < finArrlength; i++){
//				productIDs[i] = Integer.parseInt(productIdsArray[i + page.getRow()]);
//			}
//
//			//find each product. create an arraylist
//			List<Product> products_ = new ArrayList<>();
//			for(long id : productIDs){
//				Product product = productDAO.findOne(id);
//				if (product != null) {
//					product.setOrderId(order.getId());
//					products_.add(product);
//				}
//			}
//////////////////////////////////////

			return products;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateProduct(Product product) throws ServiceException {
		if (!validation(product)) {
			return false;
		}
		try {
			return productDAO.update(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean deleteProduct(long id) throws ServiceException {
		if(!validation(id)){
			return false;
		}
		try {
			return productDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long countProductsLike(Product product) throws ServiceException {
		try {
			return productDAO.countProductsLike(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> getProductsLike(Product product, int row) throws ServiceException { //ArrayList
		try {
			return productDAO.findProductsLike(product, row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}

