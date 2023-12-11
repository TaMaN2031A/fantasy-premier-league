import Card from "./Card"

export const commonCards = [
    {
        Title: "FAQ",
        description: "Answers to common queries, simplified for convenience."
    },
    {
        Title: "Rule",
        description: "Guidelines and regulations for a fair and enjoyable experience."
    },
    {
        Title: "League Tables",
        description: "Track rankings and standings across different leagues and divisions."
    },
    {
        Title: "Matches",
        description: "Upcoming, ongoing, and past game schedules and results."
    },
    {
        Title: "Players",
        description: "Profiles and stats of individual athletes or team members."
    },
    {
        Title: "Teams",
        description: "Information about various sports teams and their compositions."
    }
];

export const adminCards = [
    {
        Title: "Match Statistics",
        description: "View and manage match statistics."
    },
    {
        Title: "Promote Users",
        description: "Promote users to admin status."
    }
];

export const userCards = [
    {
        Title: "Pick Team",
        description: "Craft your winning roster with top players for your fantasy league."
    },
    {
        Title: "History",
        description: "Review past performances, scores, and achievements in your fantasy games."
    },
    {
        Title: "Groups",
        description: "Engage with fellow enthusiasts, form leagues, and join competitive groups."
    },
    {
        Title: "Transfers",
        description: "Manage player trades, acquisitions, and team adjustments for strategic gameplay."
    }
];


export const commonList = commonCards.map((card) => (
    <Card Title={card.Title} details={card.description} />
  ));

export const adminList = adminCards.map((card) => (
    <Card Title={card.Title} details={card.description} />
));

export const userList = userCards.map((card) => (
    <Card Title={card.Title} details={card.description} />
));

