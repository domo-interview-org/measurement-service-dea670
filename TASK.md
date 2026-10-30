# Task

Work through the steps in order, as you would on a real ticket: the change and its tests. Think aloud
as you go. Ask whenever something is unclear: some cases are left open on purpose.

## Step 1 · The billing rule

Bill the visit's duration at the care category's hourly rate (`CareCategory`). Rates: A 80.00, B 70.00, C 55.00 CHF per
hour. Bill in started 5-minute units: 31 minutes bills as 35. Round the amount to the nearest
0.05 CHF. After billing, notify the coordinator.

| Category | Duration | Billed minutes | Amount |
|---|---|---|---|
| C | 30 min | 30 | 27.50 |
| B | 33 min | 35 | 40.85 |
| A | 61 min | 65 | 86.65 |
