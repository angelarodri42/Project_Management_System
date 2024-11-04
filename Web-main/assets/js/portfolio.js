$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "assets/data/projects.xml",
        dataType: "xml",
        success: function (xml) {
            var i = 1;
            $(xml).find('projects').each(function () {
                var isOnGoing = $(this).find('status').text().replace(/\s/g, '');

                if (isOnGoing === 'true') {
                    return;
                }

                var intendedUseOfTheBuilding = $(this).find('intendedUseOfTheBuilding').text();
                var projectType = $(this).find('projectType').text();
                var customerName = $(this).find('customer > name').text();
                var budget = $(this).find('budget').text();
                var description = getDescription(intendedUseOfTheBuilding, projectType, customerName, budget);
                var image = getImage(i);
                i++;

                if (i >= 9) {
                    i = 1;
                }

                var projectHtml = `
                        <div class="col-12 col-md-6 col-lg-4">
                            <div class="card text-center bg-white pb-2">
                                <div class="card-body text-dark">
                                    <div class="img-area mb-4">
                                        <img src="assets/images/${image}.jpg" alt="${customerName}" class="img-fluid" style="width:90%; height: 20vh;">
                                    </div>
                                    <h3 class="card-title">${customerName}</h3>
                                    <p>${description}</p>
                                </div>
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