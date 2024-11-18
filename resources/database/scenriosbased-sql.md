**SQL Interview Questions
https://datalemur.com/questions

SQL Question 1: Analyzing Threats Detected by CrowdStrike
CrowdStrike is a cybersecurity technology company. You are given a table named threat_activity which contains data about
threats detected by CrowdStrike systems in various customer networks.

Each row in the table represents a unique threat detected at a given time for a particular customer.

The columns in the table are as follows:

threat_id: A unique identifier for the threat.

detect_time: The time the threat was detected.

threat_type: The category of the threat detected.

customer_id: The identifier for the customer in whose network the threat was detected.

location_id: The identifier for the location where the threat was detected.

The task is to write a query that will show the total number of threats detected for each customer for every day,
ranking each day by the number of threats detected.

Here is some sample data for the problem:

threat_activity Example Input:
threat_id detect_time threat_type customer_id location_id
1 2022-06-01 00:00:00 Malware C123 L789
2 2022-06-01 00:10:00 Ransomware C123 L789
3 2022-06-02 00:12:00 Phishing C456 L123
4 2022-06-02 00:15:00 Malware C456 L123
5 2022-06-02 00:00:00 Trojan C123 L789
6 2022-06-03 00:00:00 Malware C456 L123
Example Output:
date_detected customer_id total_threats rank
2022-06-01 C123 2 1
2022-06-02 C123 1 2
2022-06-02 C456 2 1
2022-06-03 C456 1 2

WITH threat_counts AS (SELECT
DATE (threat_activity.detect_time) AS date_detected
, threat_activity.customer_id
, COUNT (threat_activity.threat_id) AS total_threats
FROM
threat_activity
GROUP BY
DATE (threat_activity.detect_time),
threat_activity.customer_id
)
SELECT date_detected,
customer_id,
total_threats,
RANK() OVER (
PARTITION BY date_detected
ORDER BY total_threats DESC
) AS rank
FROM threat_counts
ORDER BY date_detected,
rank;

Other Solution:
SELECT
DATE(threat_activity.detect_time) AS date_detected,
threat_activity.customer_id,
COUNT(threat_activity.threat_id) AS total_threats,
RANK () OVER (
PARTITION BY threat_activity.customer_id
ORDER BY COUNT(threat_activity.threat_id) DESC
) rank
FROM
threat_activity
GROUP BY
date_detected,
threat_activity.customer_id
ORDER BY
date_detected,
rank;

=======================================================

This is the same question as problem #1 in the SQL Chapter of Ace the Data Science Interview!

Assume you have an events table on Facebook app analytics. Write a query to calculate the click-through rate (CTR) for
the app in 2022 and round the results to 2 decimal places.

Definition and note:

Percentage of click-through rate (CTR) = 100.0 * Number of clicks / Number of impressions
To avoid integer division, multiply the CTR by 100.0, not 100.
events Table:
Column Name Type
app_id integer
event_type string
timestamp datetime
events Example Input:
app_id event_type timestamp
123 impression 07/18/2022 11:36:12
123 impression 07/18/2022 11:37:12
123 click 07/18/2022 11:37:42
234 impression 07/18/2022 14:15:12
234 click 07/18/2022 14:16:12
Example Output:
app_id ctr
123 50.00
234 100.00
Explanation
Let's consider an example of App 123. This app has a click-through rate (CTR) of 50.00% because out of the 2 impressions
it received, it got 1 click.

To calculate the CTR, we divide the number of clicks by the number of impressions, and then multiply the result by 100.0
to express it as a percentage. In this case, 1 divided by 2 equals 0.5, and when multiplied by 100.0, it becomes 50.00%.
So, the CTR of App 123 is 50.00%.

`
SELECT app_id,
ROUND(100.0 *
SUM(CASE WHEN event_type = 'click' THEN 1 ELSE 0 END) /
NULLIF(SUM(CASE WHEN event_type = 'impression' THEN 1 ELSE 0 END), 0), 2) AS ctr
FROM events
WHERE timestamp >= '2022-01-01' AND timestamp < '2023-01-01'
GROUP BY app_id;
`
=======================================================

SQL Question 2: Filter Customers based on Multiple Conditions for CrowdStrike
CrowdStrike has a database of customers who subscribe to its cybersecurity platform. Create a SQL statement that filters down this customer database to only show customers who have:

An active subscription status
A region of 'North America' OR 'Europe'
Spend over $10,000 OR have more than 5 users in their account
Do NOT have a 'Government' sector
Assume the 'customers' table schema as:

customers Example Input:
customer_id	subscription_status	region	spend	user_count	sector
101	Active	North America	15000	3	Private
202	Inactive	Europe	5000	8	Private
303	Active	Asia	12000	6	Government
404	Active	North America	9500	4	Public
505	Active	Europe	11000	7	Private
Write a PostgreSQL query to solve this.

SELECT customer_id
FROM customers
WHERE subscription_status = 'Active'
AND region IN ('North America', 'Europe')
AND (spend > 10000 OR user_count > 5)
AND sector != 'Government';


=======================================================
SQL Question 4: Average Threat Level Reported by CrowdStrike
CrowdStrike, a cybersecurity technology firm, collects data on numerous threats it identifies each day. As part of its threat intelligence database, each row signifies a separate threat, where an associated numerical threat_level designates the threat's severity.

For each day, your task is to calculate the average threat_level of all threats identified. This will assist in gaining an understanding of the average seriousness of threats identified each day.

threats Example Input:
threat_id	date_identified	threat_level
1	09/01/2022	7
2	09/01/2022	6
3	09/02/2022	8
4	09/03/2022	9
5	09/03/2022	5
6	09/03/2022	8
7	09/04/2022	7
Example Output:
date_identified	avg_threat_level
09/01/2022	6.50
09/02/2022	8.00
09/03/2022	7.33
09/04/2022	7.00

SELECT date_identified,
ROUND(AVG(threat_level), 2) AS avg_threat_level
FROM threats
GROUP BY date_identified
ORDER BY date_identified;


=======================================================
Customer Data Filter
CrowdStrike maintains a database of information about their customers. The following table shows an example data from the customers table:

customers Example Input:
customer_id	first_name	last_name	email_domain
8945	Maria	Miley	yahoo.com
5455	John	Stewart	gmail.com
1166	Robert	Jones	hotmail.com
3685	Patricia	Brown	crowdstrike.com
7981	James	Lopez	crowdstrike.com
As part of a promotion targeted at employees of certain companies, CrowdStrike wishes to filter out customer records who have their email_domain registered as crowdstrike.com.

Can you write a PostgreSQL query to fetch information of customers whose email is registered with CrowdStrike?

SELECT customer_id,
first_name,
last_name,
email_domain
FROM customers
WHERE email_domain = 'crowdstrike.com';

=======================================================
Analyzing Customer Activities in CrowdStrike
You are a Data Analyst in CrowdStrike and your task is to analyze the customer database. You want to find out what software products are being used by each customer and also how much they use it; you also want to link this data with detailed information about the customer, like their location and their contact information.

For this purpose, you have two tables. The first table, customers, contains data related to the customers - specifically, customer_id, name, email, country and city. The second table, software_usage, stores data related to which customer uses which software product, including columns for customer_id (linked to the customer_id in the customer table), software_product, and hours_used.

customers Example Input:
customer_id	name	email	country	city
111	John Doe	johndoe@example.com	USA	Los Angeles
112	Jane Smith	janesmith@example.com	Canada	Vancouver
113	Alois Alzheimer	alois@example.com	Germany	Berlin
114	Kain Tapper	kaintapper@example.com	Finland	Helsinki
115	Gregor Mendel	gregor@example.com	Czech Republic	Brno
software_usage Example Input:
usage_id	customer_id	software_product	hours_used
1	111	Falcon Pro	200
2	112	Falcon Pro	150
3	112	Falcon Enterprise	300
4	113	Falcon Enterprise	400
5	114	Falcon Complete	350
6	115	Falcon Pro	100
7	115	Falcon Complete	250

`SELECT c.customer_id,
c.name,
c.email,
c.country,
c.city,
su.software_product,
SUM(su.hours_used) AS total_hours_used
FROM customers c
JOIN
software_usage su ON c.customer_id = su.customer_id
GROUP BY c.customer_id,
c.name,
c.email,
c.country,
c.city,
su.software_product
ORDER BY c.customer_id,
su.software_product;
`

=======================================================
 
=======================================================

=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 
=======================================================
 