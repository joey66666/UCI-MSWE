-- Question 8
-- SELECT vendor_name, vendor_contact_last_name, vendor_contact_first_name
-- FROM vendors
-- ORDER BY vendor_contact_last_name, vendor_contact_first_name ASC;


-- Question 9
-- SELECT CONCAT(vendor_contact_last_name,', ',vendor_contact_first_name)
-- AS full_name
-- FROM vendors
-- -- WHERE vendor_contact_last_name LIKE "A%"
-- -- OR vendor_contact_last_name LIKE "B%"
-- -- OR vendor_contact_last_name LIKE "C%"
-- -- OR vendor_contact_last_name LIKE "E%"
-- WHERE vendor_contact_last_name REGEXP '^A|^B|^C|^E'
-- ORDER BY vendor_contact_last_name, vendor_contact_first_name ASC;


-- Question 10
-- SELECT invoice_due_date AS 'DUE_DATE', invoice_total AS 'TOTAL',
--         invoice_total * 0.1 AS '10%', invoice_total * (1 + 0.1) AS 'Plus 10%'
-- FROM invoices
-- -- WHERE invoice_total >= 500 AND invoice_total <= 1000
-- WHERE invoice_total BETWEEN 500 AND 1000
-- ORDER BY invoice_due_date DESC;


-- Question 11
-- SELECT invoice_number,
--        invoice_total,
--        (payment_total + credit_total)                 AS payment_credit_total,
--        (invoice_total - payment_total - credit_total) AS balance_due
-- FROM invoices
-- WHERE (invoice_total - payment_total - credit_total) > 50
-- ORDER BY balance_due DESC
-- LIMIT 5;


-- Question 12
-- SELECT invoice_number, invoice_date,
--        (invoice_total - payment_total - credit_total) AS balance_due,
--        payment_date
-- FROM invoices
-- WHERE payment_date IS NULL;


-- Question 13
-- SELECT DATE_FORMAT(CURRENT_DATE(), '%m-%d-%Y') AS 'CURRENT DATE';


-- Question 14
-- SET @starting_principal := 50000;
-- SET @interest := 0.065 * @starting_principal;
-- SET @principal_plus_interest := @starting_principal + @interest;
-- SELECT @starting_principal, @interest, @principal_plus_interest;
