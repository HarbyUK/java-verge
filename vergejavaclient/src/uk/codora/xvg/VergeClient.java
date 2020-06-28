package uk.codora.xvg;

import java.util.List;

public interface VergeClient {
	public String getAddress(String account);
	
	public String getAccount(String address);
	
	public String getNewAddress(String account);
	
	public List<String> listAccounts();
	
	public List<String> getTransaction(String transactionId);
	
	public void setAccount(String address, String account);
	
	public long getBalance(String account, int minConf);
	
	public String move(String accountFrom, String accountTo, long amount);
	
	public String send(String account, String toAddress, long amount);
	
	public List<String> validateAddress(String address);
}

/*

     METHODS TO IMPLEMENT:
     
     add_multi_sig_address
     backup_wallet
     create_raw_transaction
     decode_raw_transaction
     dump_priv_key
     encrypt_wallet
     get_account_address
     get_addresses_by_account
     get_balance
     get_block
     get_block_count
     get_block_hash
     get_connection_count
     get_difficulty
     get_generate
     get_hashes_per_sec
     get_info
     get_memory_pool
     get_mining_info
     get_new_address
     get_raw_transaction
     get_received_by_account
     get_received_by_address
     get_transaction
     get_work
     help
     import_priv_key
     key_pool_refill
     list_accounts
     list_received_by_account
     list_received_by_address
     list_since_block
     list_transactions
     list_unspent
     move
     send_from
     send_many
     send_raw_transaction
     send_to_address
     set_account
     set_generate
     set_tx_fee
     sign_message
     sign_raw_transaction
     stop
     validate_address
     verify_message
     wallet_lock
     wallet_passphrase
     wallet_passphrase_change

*/