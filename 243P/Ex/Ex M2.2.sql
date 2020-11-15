-- Quesiton 1
-- SELECT * FROM  vendors INNER JOIN invoices ON vendors.vendor_id = invoices.vendor_id;


-- Question 2
-- SELECT  vendor_name, invoice_number, invoice_date,
-- 			  invoice_total - payment_total - credit_total AS balace_due
-- FROM vendors v JOIN invoices i ON v.vendor_id = i.vendor_id
-- WHERE invoice_total - payment_total - credit_total <> 0;
-- ORDER BY vendor_name ASC;


-- Quesion 3
-- SELECT vendors.vendor_name, 
-- 			 vendors.default_account_number AS default_account, 
-- 			 general_ledger_accounts.account_description AS description
-- FROM vendors JOIN general_ledger_accounts 
-- 		 ON vendors.default_account_number = general_ledger_accounts.account_number
-- ORDER BY description, vendor_name;


-- Quesition 4
-- SELECT vendor_name, invoice_date, invoice_number, 
-- 			 invoice_sequence AS li_sequence, line_item_amount AS li_amount
-- FROM vendors v JOIN invoices i ON v.vendor_id = i.vendor_id 
-- 			 JOIN invoice_line_items l ON i.invoice_id = l.invoice_id
-- ORDER BY vendor_name, invoice_date, invoice_number, invoice_sequence;


-- Question 5
-- SELECT v.vendor_id, v.vendor_name, 
-- 		   CONCAT(v.vendor_contact_first_name,' ',v.vendor_contact_last_name) AS contact_name 
-- FROM vendors v JOIN vendors w ON v.vendor_contact_last_name = w.vendor_contact_last_name
-- 			 AND v.vendor_id <> w.vendor_id
-- ORDER BY v.vendor_contact_last_name;
				
				
-- Question 6
-- SELECT g.account_number, g.account_description, i.invoice_id
-- FROM general_ledger_accounts g LEFT OUTER JOIN invoice_line_items i 
-- 			 ON g.account_number = i.account_number
-- WHERE i.invoice_id IS NULL;


-- Question 7
-- SELECT vendor_name, 'CA' AS vendor_state FROM vendors WHERE vendor_state = 'CA'
-- UNION
-- SELECT vendor_name, 'Outside CA' AS vendor_state FROM vendors WHERE vendor_state <> 'CA'
-- ORDER BY vendor_name;
