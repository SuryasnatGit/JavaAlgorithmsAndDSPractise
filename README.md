# JavaAlgorithmsAndDSPractise



# Design Problems

1. Design a system to monitor the exceptions generated by various applications and servers.

Probably, each machine must log to its own machine first, and then perform an integration on its own machine. After the integration, it will be collectively sent to a central node for storage at regular intervals. Integration includes, for example, a request occurs 10 times in a second,
There is no need to record 10 requests, right?

At first glance, there are two ways of thinking. Don't spray me:
a. Using log, such as grabbing the server's exception log as a counter, design an asynchronous log integration system. Or directly import to logstash for aggregation.
b. Use the underlying byte stream software of jvm, such as libagent like profiler or appdanamics in byte stream Grab and monitor abnormal events at the level

2. 5.1 Design the function of prompting typos in word. Follow up is what advanced features can be added.
5.2 top k visited URLs in last 24 hr/1 hr/5min. Finally, there is a follow up how to ensure the speed of response to users around the world.