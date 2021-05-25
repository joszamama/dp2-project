# README.txt
#
# Copyright (C) 2012-2021 Rafael Corchuelo.
#
# In keeping with the traditional purpose of furthering education and research, it is
# the policy of the copyright owner to permit non-commercial use and redistribution of
# this software. It has been tested carefully, but it is not guaranteed for any particular
# purposes.  The copyright owner does not offer any warranties or representations, nor do
# they accept any liabilities with respect to them.

This is Acme Planner, our D02 project in Design & Testing II. We are implementing several functionalities like Shouting and Task control.

During the review of 03/05/2021 we talked with Mr. Rafael Corchuelo about a bug in the framework. There is a bug in which workflow-delete does not perform validations and therefore there is no way to check if a task associated to a workplan can or cannot be deleted. This causes that you always end up in panic.

As requested in the course, the publication has been made in CleverCloud. Both us and many other students have had many problems, starting with typos in the slides where the steps to follow were explained to failures of CleverCloud itself that does not show views that in the project work without any problems.

We think that some of these problems may be due to using /manager/ as address, since TomCat servers use that range as reserved address. In the code requested in the requirements everything works without any problem.

Another problem we have found that was comunicated to Rafael was about testing, where some tests could not perform more than 60
60%. Since every test is already commented, we described the problem there, which was about the bind() unbind() functions not being used, however in the test they were used.

The link to our GitHub repository is: https://github.com/joszamama/dp2-project
The link to our GitHub release is: https://github.com/joszamama/dp2-project/releases/tag/2.1.2
