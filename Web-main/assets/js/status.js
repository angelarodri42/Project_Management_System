$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "assets/data/projects.xml",
        dataType: "xml",
        success: function (xml) {
            var i = 1;
            $(xml).find('projects').each(function () {
                var isOnGoing = $(this).find('status').text().replace(/\s/g, '');
                var projectType = $(this).find('projectType').text();
                var customerName = $(this).find('customer > name').text();
                var budget = $(this).find('budget').text();
                var image = getImage(i);
                var manHoursUsed = $(this).find('manHoursUsed').text();
                var materialExpenses = $(this).find('materialExpenses').text();
                var badgeClass = isOnGoing === 'true' ? 'badge bg-info' : 'badge bg-success';
                var badgeText = isOnGoing === 'true' ? 'In Progress' : 'Completed';
                i++;

                if (i >= 9) {
                    i = 1;
                }

                var projectHtml = `
                        <div class="row mb-5 mt-5 bg-light border">
                        <div class="col-md-3 p-0">
                            <div class="square-image">
                                <img src="assets/images/${image}.jpg" alt="Project 1 Image" class="img-fluid">
                            </div>
                        </div>
                        <div class="col-md-7 pt-3" style="margin-left: 30px;">
                            <h3>${customerName}</h3>
                            <p>${projectType}</p>
                            <p>Man hours used: ${manHoursUsed}</p>
                            <p>Material expenses: ${materialExpenses}</p>
                            <p>Budget: ${budget}</p>
                        </div>
                        <div class="col-md-1 d-flex align-items-center pt-3 pb-3 justify-content-center">
                            <span class="${badgeClass}">${badgeText}</span>
                        </div>
                    </div>
                    `;

                $('#projectList').append(projectHtml);
            });
        },
        error: function (xhr, status, error) {
            console.error('Error loading XML:', error);
        }
    });
});

function getImage(i) {
    return `portfolio-building${i}`;
}

function getDescription(intendedUse, projectType, customerName, budget) {
    return `${projectType} ${intendedUse} for ${customerName} with budget ${budget}.`;
}