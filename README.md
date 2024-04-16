# WebCrawler

Components and system design: 

CrawlerService - crawls the url recursively, parsing the HTML and collecting all the links

TrieService - trie data structure to hold the urls efficiently
the trie datastructure ensures that a page will not be downloaded more than once
bottlenecks - the current design handles the requests synchronously, processing medium/large size which is very time consuming
also, the current design cannot handle concurrent requests, only one request at a time. 

The current design can be improved by

a.adding concurrency to handle multiple requests in a queue,
each rest request will add the url to a queue, worker threads will read from the queue async and process the urls - pub sub model

b.adding cache such as redis to retrieve if a certain url was already downloaded 

c.different approach can be adding multiple services that will handle crawl requests and a load balancer to equally distribute the requests
