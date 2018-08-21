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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public List<Product> findProductsByOrder(Order order) throws ServiceException {
		try {
			long [] ids = Stream
					.of(order.getProductIds().split(","))
					.mapToLong(Long::parseLong)
					.toArray();
			/*
			getting all types of products according to the the order's products ids field
			 */
			List<Product> products = productDAO.findProductsByIds(ids);
			/*
			check if the ids[] equals to the products, that was found
			when true, order's field productIds requires to be updated
			 */
			if (ids.length != products.size()) {
				List<Long> idsList = products
						.stream()
						.map(Product::getId)
						.collect(Collectors.toList());
				String id_s = idsList.toString().replaceAll("\\[|\\]| ", "");
				order.setProductIds(id_s);
				productDAO.updateProductsInOrder(order);
			}
			return products;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> subList(List<Product> products, Page page) throws ServiceException {
		try {
			int fromIndex = (page.getCurrentPage() - 1) * page.getMaxRowsAtPage();
			int toIndex = page.getCurrentPage() == page.getMaxPage() ? products.size() : fromIndex + page.getMaxRowsAtPage();
			return products.subList(fromIndex, toIndex);
		} catch (Exception e) {
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

