<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here
		</script>
  	</jsp:attribute>
	<jsp:body>
	
		<c:if test="${ codeInsta != null }">
			<div class="container">
				<div class="row">
						<div class="col-3">You are connected</div>
						<div class="col-9">
						
							<h1>Information about myself</h1>
							Pseudo  : <c:out value="${ self.data.username }" />
								<br />
							Description  : <c:out value="${ self.data.bio }" />
								<br />
							
						</div>			
				</div>
				<img src="${ self.data.profilePicture }" alt="profile picture"> 
			</div>

			
		</c:if>
		<c:if test="${ codeInsta == null }">
			Wanna get started with instagram, please authorize the use of your date <a
				href="https://api.instagram.com/oauth/authorize/?client_id=93126b0c5bb548de9e681319c9d89d99&redirect_uri=http://localhost:9999/instaOAuth/redirect_uri&response_type=code">here</a>
		
		</c:if>
		
	</jsp:body>
</tags:template>