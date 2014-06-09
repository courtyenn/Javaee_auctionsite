        <div class="navbar navbar-default" role="navigation">
        <div class="container">
        <div class="row">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Lab 4</a>
          </div>
          <div class="navbar-collapse collapse row">
            <ul class="nav navbar-nav">
               <li><a href="${pageContext.request.contextPath}/item/create">Create a bid</a></li>
              <li><a href="#">Link</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Test <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${pageContext.request.contextPath}/item/1">Item #1</a></li>
                  <li><a href="${pageContext.request.contextPath}/item/2">Item #2</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
              <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
          </div><!--/.nav-collapse -->
                    </div>
        </div><!--/.container-fluid -->
      </div>