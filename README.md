# play-ebean-multitenant-example

This is an example service implementation for a multi-tenant system with tenant specific database.

## Implementation Details

In this example, we have a 'master' database. This database has 'Tenant' table which stores all the tenant information. Refer [Tenant.java] (https://github.com/kcthota/play-ebean-multitenant-example/blob/master/app/master/Tenant.java).

And each tenant has it's own database and in our example we have 'User' table defined in these tenant databases. Refer [User.java] (https://github.com/kcthota/play-ebean-multitenant-example/blob/master/app/models/User.java).

Every HTTP call will have the tenantId value in request headers, which is used to connect to the appropriate database. Using Play's Action composition mechanism, we created TenantAwareAction which would be executed for all annotated classes or methods.

In [TenantAwareAction.java] (https://github.com/kcthota/play-ebean-multitenant-example/blob/master/app/actions/TenantAwareAction.java), we check for tenantId and configure a EbeanServer, if not already available, for that tenantId. Post execution of TenantAwareAction, the call would be delegated to appropriate controller method. 

In our example, [UserController.java] (https://github.com/kcthota/play-ebean-multitenant-example/blob/master/app/controllers/UserController.java) has getUsers method which is annotated as [TenantAware] (https://github.com/kcthota/play-ebean-multitenant-example/blob/master/app/annotations/TenantAware.java). In this method, we can get tenant specific EBeanServer object for performing the database operation.

## License:

Copyright 2015 Krishna Chaitanya Thota (kcthota@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.