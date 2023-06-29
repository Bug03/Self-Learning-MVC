package service.impl;


import java.util.List;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import model.Catalog;
import model.Product;
import service.CategoryService;

public class CategoryServicesImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Catalog category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Catalog newCategory) {
		Catalog oldCate = categoryDao.get(Integer.parseInt(newCategory.getId()));
		oldCate.setName(newCategory.getName());
		oldCate.setParent_id(newCategory.getParent_id());
		categoryDao.edit(oldCate);

	}

	@Override
	public void delete(String id) {
		categoryDao.delete(id);

	}

	@Override
	public Catalog get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Catalog get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Catalog> getAll() {
		return categoryDao.getAll();
	}
	
	
	public List<Catalog> getCateByProduct(int id) {
		return categoryDao.getCateByProduct(id);
	}

    @Override
    public int getToltal() {
        return categoryDao.getToltal();
    }

    @Override
    public List<Catalog> paging(int index) {
        return categoryDao.paging(index);
    }

}