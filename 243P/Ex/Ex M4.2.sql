-- Question 1
-- SELECT DISTINCT vendor_name 
-- FROM vendors WHERE vendor_id IN (SELECT vendor_id FROM invoices)
-- ORDER BY vendor_name;


-- Question 2
-- SELECT * FROM invoices 
-- WHERE payment_total > (SELECT AVG(payment_total) FROM invoices WHERE payment_total > 0)
-- ORDER BY invoice_total DESC;


-- Question 3
-- SELECT account_number, account_description
-- FROM general_ledger_accounts 
-- WHERE NOT EXISTS
-- 		(SELECT account_number FROM invoice_line_items WHERE general_ledger_accounts.account_number = invoice_line_items.account_number)
-- ORDER BY account_number;


-- Question 4
-- SELECT vendors.vendor_name, invoices.invoice_id, invoice_sequence, line_item_amount
-- FROM vendors JOIN invoices ON vendors.vendor_id = invoices.vendor_id
-- 			JOIN invoice_line_items ON invoices.invoice_id = invoice_line_items.invoice_id
-- WHERE invoice_line_items.invoice_id IN (SELECT invoice_id FROM invoice_line_items WHERE invoice_sequence > 1)
-- ORDER BY vendors.vendor_name, invoice_id, invoice_sequence;


-- Question 5
-- SELECT vendor_id, MAX(invoice_total)
-- FROM invoices WHERE (invoice_total - payment_total - credit_total) > 0
-- GROUP BY vendor_id;

-- SELECT SUM(invoice_total_max)
-- FROM (SELECT vendor_id, MAX(invoice_total) AS invoice_total_max
-- 			FROM invoices WHERE (invoice_total - payment_total - credit_total) > 0
-- 			GROUP BY vendor_id) AS tmp;


-- Question 6
-- SELECT version(), @@sql_mode;
-- SET sql_mode = (SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

-- SELECT vendor_name, vendor_city, vendor_state
-- FROM vendors
-- WHERE CONCAT(vendor_state, ' ', vendor_city) NOT IN 
--     (SELECT CONCAT(vendor_state, ' ', vendor_city)
--      FROM vendors
--      GROUP BY CONCAT(vendor_state, ' ', vendor_city)
--      HAVING COUNT(vendor_state) > 1)
-- ORDER BY vendor_state, vendor_city;
 

-- Question 7
-- SELECT vendor_name, invoice_number, invoice_date, invoice_total 
-- FROM vendors JOIN invoices ON vendors.vendor_id = invoices.vendor_id
-- WHERE invoice_date IN
-- 		(SELECT MIN(invoice_date) FROM invoices WHERE invoices.vendor_id = vendors.vendor_id)
-- ORDER BY vendor_name;