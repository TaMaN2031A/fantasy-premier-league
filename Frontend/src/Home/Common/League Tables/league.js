import React, {useState} from 'react';
import {useQuery} from "react-query";
import {fetchLeagueStandings, fetchTopAssists, fetchTopScorers} from "../../../Services/League Tables/Tables";
import Card from "./Card";
export const League = () => {
    const {data: league, isLoading: isLoading1, error: error1} = useQuery('League' , fetchLeagueStandings, {refetchOnWindowFocus: false});
    const {data: TopScorer, isLoading: isLoading2, error: error2} = useQuery('TopScorer' , fetchTopScorers, {refetchOnWindowFocus: false});
    const {data: TopAssisted, isLoading: isLoading3, error: error3} = useQuery('TopAssisted' , fetchTopAssists, {refetchOnWindowFocus: false});
    const {data: TopCleanSheet, isLoading: isLoading4, error: error4} = useQuery('TopCleanSheet' , fetchTopAssists, {refetchOnWindowFocus: false});


    if(isLoading1 || isLoading2 || isLoading3 || isLoading4){
        return <Loading />
    }
    if (error1 || error2 || error3 || error4) {
        return <p>Error loading users</p>;
    }

    return (
        <section className="flex flex-row bg-gradient-to-r from-slate-800 to-gray-900">
            <div className="flex flex-col items-center justify-center px-1 py-2 mx-auto md:h-screen lg:py-0">
                <div className="mx-auto max-w-screen-xl px-2 lg:px-12">
                    <div className="relative overflow-x-auto">
                        <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                            <thead
                                className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="px-6 py-3">
                                    ID
                                </th>
                                <th scope="col" className="px-6 py-3">
                                    name
                                </th>
                                <th scope="col" className="px-6 py-3">
                                    scored
                                </th>
                                <th scope="col" className="px-6 py-3">
                                    conceded
                                </th>
                                <th scope="col" className="px-6 py-3">
                                    diff
                                </th>
                                <th scope="col" className="px-6 py-3">
                                    points
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                league.map((item, index) => (
                                    <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">

                                        <td className="px-6 py-4">
                                            {index}
                                        </td>
                                        <td className="px-6 py-4">
                                            {item.name}
                                        </td>
                                        <td className="px-6 py-4">
                                            {item.goals_for}
                                        </td>
                                        <td className="px-6 py-4">
                                            {item.goals_conceded}
                                        </td>
                                        <td className="px-6 py-4">
                                            {item.goals_for - item.goals_conceded}
                                        </td>
                                        <td className="px-6 py-4">
                                            {item.points}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div className="flex flex-col items-center justify-center px-1 py-2 mx-auto md:h-screen lg:py-0">
                <Card data={TopScorer} criteria="Top Scorer"/>
            </div>
            <div className="flex flex-col items-center justify-center px-1 py-2 mx-auto md:h-screen lg:py-0">
                <Card data={TopAssisted} criteria="Top Assisters"/>
            </div>
            <div className="flex flex-col items-center justify-center px-1 py-2 mx-auto md:h-screen lg:py-0">
                <Card data={TopCleanSheet} criteria="Top Clean Sheet"/>
            </div>
        </section>
    );
}


const Loading = () => (
    <section className="bg-gradient-to-r from-slate-800 to-gray-900">
        <div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
            <div role="status"
                 className="max-w-md p-4 space-y-4 border border-gray-200 divide-y divide-gray-200 rounded shadow animate-pulse dark:divide-gray-700 md:p-6 dark:border-gray-700">
                <div className="flex items-center justify-between">
                    <div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                        <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                    </div>
                    <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                </div>
                <div className="flex items-center justify-between pt-4">
                    <div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                        <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                    </div>
                    <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                </div>
                <div className="flex items-center justify-between pt-4">
                    <div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                        <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                    </div>
                    <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                </div>
                <div className="flex items-center justify-between pt-4">
                    <div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                        <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                    </div>
                    <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                </div>
                <div className="flex items-center justify-between pt-4">
                    <div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                        <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                    </div>
                    <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                </div>
                <span className="sr-only">Loading...</span>
            </div>
        </div>
    </section>
);