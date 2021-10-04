<div class="navigation">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Company
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/company/createCompany">Crete company</a>
            <a href="${pageContext.request.contextPath}/company/findCompany">Find by ID</a>
            <a href="${pageContext.request.contextPath}/company/updateCompany">Update company</a>
            <a href="${pageContext.request.contextPath}/company/deleteCompany">Delete company</a>
            <a href="${pageContext.request.contextPath}/company/allCompanies">View all companies</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Developer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/developer/createDeveloper">Create developer</a>
            <a href="${pageContext.request.contextPath}/developer/findDeveloper">Find by ID</a>
            <a href="${pageContext.request.contextPath}/developer/updateDeveloper">Update developer</a>
            <a href="${pageContext.request.contextPath}/developer/deleteDeveloper">Delete developer</a>
            <a href="${pageContext.request.contextPath}/developer/allDevelopers">View all developers</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Project
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/project/createProject">Create Project</a>
            <a href="${pageContext.request.contextPath}/project/findProject">Find by ID</a>
            <a href="${pageContext.request.contextPath}/project/updateProject">Update project</a>
            <a href="${pageContext.request.contextPath}/project/deleteProject">Delete project</a>
            <a href="${pageContext.request.contextPath}/project/allProjects">View all projects</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Customer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/customer/createCustomer">Create customer</a>
            <a href="${pageContext.request.contextPath}/customer/findCustomer">Find by ID</a>
            <a href="${pageContext.request.contextPath}/customer/updateCustomer">Update customer</a>
            <a href="${pageContext.request.contextPath}/customer/deleteCustomer">Delete customer</a>
            <a href="${pageContext.request.contextPath}/customer/allCustomers">View all customers</a>
        </div>
    </div>
    <div class="dropdown">
    <button class="dropbtn">Other operation
        <i></i>
    </button>
    <div class="dropdown-content">
        <a href="${pageContext.request.contextPath}/query/getDeveloperByProject">Developers on project</a>
        <a href="${pageContext.request.contextPath}/query/getSalary">Developers salary</a>
        <a href="${pageContext.request.contextPath}/query/getLevel">Developers by level</a>
        <a href="${pageContext.request.contextPath}/query/getLanguage">Developers by language</a>
    </div>
</div>
</div>
