<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here 
		</script>
  	</jsp:attribute>  
	<jsp:body>
		<h1>Hello <c:out value="${ name }" /></h1>
		<p>This is just an example page.</p>
	
		<c:if test="${ codeInsta != null }">
			you are connected<br/>
			<c:out value="${ self }" />
		</c:if>
		<c:if test="${ codeInsta == null }">
			Wanna get started with instagram, please authorize the use of your date <a href="https://api.instagram.com/oauth/authorize/?client_id=93126b0c5bb548de9e681319c9d89d99&redirect_uri=http://localhost:9999/instaOAuth/redirect_uri&response_type=code">here</a>
		
		</c:if>
		
	</jsp:body>
</tags:template>