<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/profileUpdated" commandName="member">
<table class="formtable">
<tr><td>Email:</td><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td>Address Line 1</td><td><sf:input name = "ad_line1" path="ad_line1" type="text"/><br/><sf:errors path="ad_line1" cssClass="error"></sf:errors></td></tr>
<tr><td>Address Line 2</td><td><sf:input name = "ad_line2" path="ad_line2" type="text"/><br/><sf:errors path="ad_line2" cssClass="error"></sf:errors></td></tr>
<tr><td>City/Town</td><td><sf:input name = "ad_city" path="ad_city" type="text"/><br/><sf:errors path="ad_city" cssClass="error"></sf:errors></td></tr>
<tr><td>County</td><td><sf:input name = "ad_county" path="ad_county" type="text"/><br/><sf:errors path="ad_county" cssClass="error"></sf:errors></td></tr>
<tr><td>Contact Number</td><td><sf:input name = "contact_num" path="contact_num" type="text"/><br/><sf:errors path="contact_num" cssClass="error"></sf:errors></td></tr>
<tr><td>Emergency Contact Name</td><td><sf:input name = "em_con_name" path="em_con_name" type="text"/><br/><sf:errors path="em_con_name" cssClass="error"></sf:errors></td></tr>
<tr><td>Emergency Contact Number</td><td><sf:input name = "em_con_num" path="em_con_num" type="text"/><br/><sf:errors path="em_con_num" cssClass="error"></sf:errors></td></tr>
<sf:hidden name = "authority" path="authority"/>
<tr><td><input value="Change Details" type="submit"/></td></tr>

</table>
</sf:form>