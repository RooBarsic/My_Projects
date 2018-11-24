<h2> Coursework in database designing  based on the game MassEffect. </h2>

Mass Effect is a computer game in the genre of role-playing action movie, developed by BioWare and released by Microsoft Game Studios in 2007, the first part of the Mass Effect series. ( https://en.wikipedia.org/wiki/Mass_Effect_(video_game) )

<h4> In this paper, we need to automate: </h4>
1) The collection of resources and minerals from the surface of planets and satellites (which will make the study of planets and satellites less tedious and boring).

2) The interaction of the protagonist with numerous traders and inventory.

3) The search for the optimal route to move from one Solar System to another (there are possibilities to choose the shortest path or the minimum number of flights between the Solar Systems).

(SS - Solar System)

<h4> Possible options for queries to our database: </h4>

1) What minerals are there on the surface of a given planet / satellite, and in what quantity?
2) Which planets / satellites (+ solar system) have this resource?
3) On which planets / satellites - can you find a resource - in quantities not less than x?
4) Collect x units of resource y from the planet / satellite. (character and planets / satellite - must be in the same SS)
5) Objects of this station?
6) At which stations you can buy this item (in some quantity) + price?
7) At which stations you can buy this item + price (order by price)?
8) Buy x item units at station y. (character and station - must be in the same solar system)
9) Sell ​​x units of this item - station y. (character and station - must be in the same solar system)
10) Estimated estimate for flight from SS1 to SS2 (shortest path)
11) Estimated estimate for flight from SS1 to SS2 (selection of the minimum number of flights between SS)
12) Which SS / repeaters need to go through for a flight from SS1 to SS2?

<h4> Combined queries </h4>

13) On which planets / satellites (+ solar system) is this resource sorted by quantity or rating of interlacing?
14) At which stations you can buy this item + price (order in ascending price) Sort according to interchange estimate?
15) On which SS can you find these resources (within one SS) Sort according to interlacing evaluation?
    
<h4> To optimize query processing, add B-Tree and Hash indexes on some tables. </h4>

<h4> In the course of the course work - the PostgreSQL object-relational database management system was used. </h4>

<h4> Details in the reports </h4>
    
