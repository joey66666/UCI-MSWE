-- SELECT vendor_name FROM vendors

SELECT COUNT(*) AS number_of_invoces,
       SUM(invoice_total) AS grand_invoice_total
FROM invoices