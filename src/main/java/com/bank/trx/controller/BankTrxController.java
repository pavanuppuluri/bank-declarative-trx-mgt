package com.bank.trx.controller;

import java.util.List;

import com.bank.trx.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.trx.model.Account;
import com.bank.trx.model.AccountTransferRestModel;

@RestController
@RequestMapping("/services")
public class BankTrxController {

	@Autowired
	@Qualifier("declarativeTrxManagementBean")
	private IAccountService declAccountService;

	@GetMapping("declarative/accounts")
	public ResponseEntity<List<Account>> getAllDeclAccounts() {
		List<Account> list = declAccountService.getAllAccounts();
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	@PostMapping("declarative/transfer")
	public ResponseEntity<Void> decltransferAccntToAccnt(
			@RequestBody AccountTransferRestModel accountTransferRestModel) {

		declAccountService.transferMoney(accountTransferRestModel.getFrom(), accountTransferRestModel.getTo(),
				accountTransferRestModel.getAmount(), 5);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
