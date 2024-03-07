<?php

namespace App\Http\Controllers;

use App\Neo4j\Driver;
use App\Models\Movie;
use Illuminate\Support\Arr;
use Illuminate\Support\Facades\Auth;

use function PHPSTORM_META\map;

class HomeController extends Controller
{
    protected $neo4jDriver;

    public function __construct(Driver $neo4jDriver)
    {
        $this->neo4jDriver = $neo4jDriver;
    }

    public function view()
    {
        $recommended_movies = array();
        $top_movies = array();

        if (Auth::user()) {
            $cypher = '
                MATCH (u1:User {id: $userId})<-[:CREATED_BY]-(c1:Comment)-[:COMMENTED_ON]->(m1:Movie)
                WHERE c1.rating >= 3
                                
                MATCH (u2:User)<-[:CREATED_BY]-(:Comment)-[:COMMENTED_ON]->(m2:Movie)
                WHERE (m1:Movie)<-[:COMMENTED_ON]-(:Comment)-[:CREATED_BY]->(u2) AND u2 <> u1

                MATCH (u2:User)<-[:CREATED_BY]-(c2:Comment)-[:COMMENTED_ON]->(recommendedMovie:Movie)
                WHERE NOT (u1:User)<-[:CREATED_BY]-(:Comment)-[:COMMENTED_ON]->(recommendedMovie:Movie)
                AND c2.rating >= 3
                RETURN DISTINCT recommendedMovie
            ';

            $results = $this->neo4jDriver->run($cypher, ['userId' => Auth::user()->id]);
            $recommended_movies_id = array_map(fn ($record) => $record['recommendedMovie']['properties']['id'], (array) $results->toArray());

            $recommended_movies = Movie::whereIn('id', $recommended_movies_id)->get();
            $top_movies = Movie::whereNotIn('id', $recommended_movies_id)->get();
        } else {
            $top_movies = Movie::all();
        }

        return view('home', [
            'recommended_movies' => $recommended_movies,
            'top_movies' => $top_movies
        ]);
    }
}
