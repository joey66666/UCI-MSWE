-- Question 1
-- SELECT vendor_id, SUM(invoice_total) FROM invoices GROUP BY vendor_id;


-- Question 2
-- SELECT vendor_name, SUM(payment_total) 
-- FROM vendors JOIN invoices ON vendors.vendor_id = invoices.vendor_id
-- GROUP BY vendor_name
-- ORDER BY SUM(payment_total) DESC;


-- Question 3
-- SELECT vendor_name, COUNT(vendor_name), SUM(invoice_total) 
-- FROM vendors JOIN invoices ON vendors.vendor_id = invoices.vendor_id
-- GROUP BY vendor_name
-- ORDER BY COUNT(invoice_id) DESC;


-- Question 4
-- SELECT account_description, COUNT(account_description), SUM(line_item_amount) 
-- FROM general_ledger_accounts JOIN invoice_line_items
-- 	ON general_ledger_accounts.account_number = invoice_line_items.account_number
-- GROUP BY account_description
-- HAVING COUNT(account_description) > 1
-- ORDER BY SUM(line_item_amount) DESC;


-- Question 5
-- SELECT account_description, COUNT(account_description), SUM(line_item_amount) 
-- FROM general_ledger_accounts JOIN invoice_line_items
-- 	ON general_ledger_accounts.account_number = invoice_line_items.account_number
-- 	JOIN invoices ON invoice_line_items.invoice_id = invoices.invoice_id
-- WHERE invoice_date BETWEEN '2018-4-1' AND '2018-6-30'
-- GROUP BY account_description
-- HAVING COUNT(account_description) > 1
-- ORDER BY SUM(line_item_amount) DESC;


-- Question 6
-- SELECT account_number, SUM(line_item_amount) 
-- FROM invoice_line_items
-- GROUP BY account_number WITH ROLLUP;


-- Question 7
-- SELECT vendor_name, COUNT(DISTINCT invoice_line_items.account_number)
-- FROM vendors JOIN invoices ON vendors.vendor_id = invoices.vendor_id
-- JOIN invoice_line_items ON invoice_line_items.invoice_id = invoices.invoice_id
-- GROUP BY vendor_name
-- HAVING COUNT(DISTINCT invoice_line_items.account_number) > 1;


-- Question 8
-- SELECT IF(GROUPING(terms_id) = 1, 'terms_id', terms_id) AS terms_id, 
-- 			 IF(GROUPING(vendor_id) = 1, 'vendor_id', vendor_id) AS vendor_id,
-- 			 MAX(payment_date), SUM(invoice_total - payment_total - credit_total)
-- FROM invoices
-- GROUP BY terms_id, vendor_id WITH ROLLUP;