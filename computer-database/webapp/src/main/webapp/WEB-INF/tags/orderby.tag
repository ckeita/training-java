<%@ attribute name="target" required="true" type="java.lang.String" %>
<%@ attribute name="column" required="true" type="java.lang.String" %>
<th>
    <a href="${target}ASC"><i class="fa fa-fw fa-sort-asc" aria-hidden="true"></i></a>
    ${column}
    <a href="${target}DESC"><i class="fa fa-fw fa-sort-desc" aria-hidden="true"></i></a>
</th>
