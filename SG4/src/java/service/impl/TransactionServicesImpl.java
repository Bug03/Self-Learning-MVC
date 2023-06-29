package service.impl;

import java.util.List;

import dao.TransactionDao;
import dao.impl.TransactionDaoImpl;
import model.Admin;
import model.Transactions;
import service.TransactionService;

public class TransactionServicesImpl implements TransactionService {

    TransactionDao transactionDao = new TransactionDaoImpl();

    public void insert(Transactions transaction) {
        transactionDao.insert(transaction);
    }

    @Override
    public void edit(Transactions transaction) {
        Transactions oldTransaction = transactionDao.get(transaction.getId());
        oldTransaction.setUser_name(transaction.getUser_name());
        oldTransaction.setUser_mail(transaction.getUser_mail());
        oldTransaction.setUser_phone(transaction.getUser_phone());
        oldTransaction.setAddress(transaction.getAddress());
        oldTransaction.setMessage(transaction.getMessage());
        oldTransaction.setAmount(transaction.getAmount());
        oldTransaction.setPayment(transaction.getPayment());
        oldTransaction.setStatus(transaction.getStatus());
        oldTransaction.setAdmin_id(transaction.getAdmin_id());
        oldTransaction.setConfirm(transaction.isConfirm());
        transactionDao.edit(oldTransaction);
    }

    @Override
    public void delete(String id) {
        transactionDao.delete(id);

    }

    @Override
    public Transactions get(int id) {
        return transactionDao.get(id);
    }

    @Override
    public List<Transactions> get(String name) {
        return transactionDao.get(name);
    }

    @Override
    public List<Transactions> getAll() {
        return transactionDao.getAll();
    }

    @Override
    public int getTotalTransactions() {
        return transactionDao.getTotalTransactions();
    }

    @Override
    public List<Transactions> paging(int index) {
        return transactionDao.paging(index);
    }
}
